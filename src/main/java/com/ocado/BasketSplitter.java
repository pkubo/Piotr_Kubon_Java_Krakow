package com.ocado;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketSplitter {
    private final Map<String, List<String>> productDeliveryMethods;

    public BasketSplitter(String absolutePathToConfigFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        productDeliveryMethods = mapper.readValue(Paths.get(absolutePathToConfigFile).toFile(), Map.class);
    }

    public String most(List<String> items)
    {
        if (items == null || items.isEmpty())
        {
            return null;
        }
        Map<String, Integer> itt = new HashMap<>();
        for (String produkt : items)
        {
            List<String> ava = productDeliveryMethods.getOrDefault(produkt, new ArrayList<>());
            for (String option : ava) {
                itt.put(option, itt.getOrDefault(option, 0) + 1);
            }
        }
        String com_option = null;
        int maxitt = 0;
        for (Map.Entry<String, Integer> entry : itt.entrySet())
        {
            String opc = entry.getKey();
            int itt2 = entry.getValue();
            if (itt2 > maxitt)
            {
                com_option = opc;
                maxitt = itt2;
            }
        }
        return com_option;
    }

    public Map<String, List<String>> save(List<String> items,String com_option)
    {
        Map<String, List<String>> res = new HashMap<>();
        List<String> prod = new ArrayList<>();
        for (String product : items)
        {
            List<String> val = productDeliveryMethods.get(product);
            if (val == null || val.isEmpty())
            {
                return null;
            }
            else if(val.contains(com_option))
            {
                prod.add(product);
            }
        }
        res.put(com_option, prod);
        return res;
    }


    public List<String> red(List<String> items,String com_option)
    {
        List<String> val = new ArrayList<>();
        for (String product : items)
        {
            List<String> at = productDeliveryMethods.get(product);
            if (at == null || at.isEmpty())
            {
                return null;
            }
            else if(at.contains(com_option))
            {

            }
            else
            {
                val.add(product);
            }
        }
        return val;
    }

    public Map<String, List<String>> split(List<String> items) {
        Map<String, List<String>> deliveryGroups = new HashMap<>();
        while(items != null && !items.isEmpty())
        {
            Map<String, List<String>> res = save(items, most(items));
            deliveryGroups.putAll(res);
            items = red(items, most(items));
        }
        return deliveryGroups;
    }


}
