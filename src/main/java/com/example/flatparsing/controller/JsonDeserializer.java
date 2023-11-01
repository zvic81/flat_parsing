package com.example.flatparsing.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.example.flatparsing.model.Address;

public class JsonDeserializer {
    ObjectMapper objectMapper = new ObjectMapper();
       public List<Address> getAddressesFromJson(String jsonBody) throws IOException {
            List<Address> addressList = new ArrayList<>();
            JsonNode rootNode = objectMapper.readTree(jsonBody);
            JsonNode itemsNode = rootNode.get("items");
            if (itemsNode != null && itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    JsonNode geoNode = itemNode.get("geo");
                    if (geoNode != null) {
                        JsonNode formattedAddressNode = geoNode.get("formattedAddress");
                        if (formattedAddressNode != null) {
                            String formattedAddress = formattedAddressNode.asText();
//                            System.out.println("formattedAddress: " + formattedAddress);
                            Address address = new Address(formattedAddress);
                            addressList.add(address);
                        }
                    }
                }
            }
    return addressList;
    }

   }


