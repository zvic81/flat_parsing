package com.example.flatparsing.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flatparsing.repo.AddresRepo;
import com.example.flatparsing.model.Address;
import com.example.flatparsing.model.Flat;

@Service
public class JsonDeserializer {
    private final AddresRepo addresRepo;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public JsonDeserializer(AddresRepo addresRepo) {
        this.addresRepo = addresRepo;
    }

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
    public List<Flat> getFlatFromJson(String jsonBody) throws IOException {
        List <Flat> flatList = new ArrayList<>();
        JsonNode rootNode = objectMapper.readTree(jsonBody);
        JsonNode itemsNode = rootNode.get("items");
        if (itemsNode != null && itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                Flat flat = new Flat();
                flat.setId_avito(itemNode.get("id").asLong());
                flat.setTitle(itemNode.get("title").asText());
                flat.setDescription(itemNode.get("description").asText());
                flat.setCurrent_price(itemNode.get("priceDetailed").get("string").asLong());
                flat.setDeleted(false);
                String formattedAddress = itemNode.get("geo").get("formattedAddress").asText();
                Address address = addresRepo.findFirstByFormattedAddress(formattedAddress);
                flat.setAddress(address);

                flatList.add(flat);
            }
        }
        return flatList;

    }
   }

/**
 * import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 * import com.fasterxml.jackson.annotation.JsonProperty;
 *
 * @JsonIgnoreProperties(ignoreUnknown = true)
 * public class Flat {
 *     @JsonProperty("id")
 *     private long id;
 *
 *     @JsonProperty("categoryId")
 *     private int categoryId;
 *
 *     @JsonProperty("locationId")
 *     private int locationId;
 *
 *     @JsonProperty("title")
 *     private String title;
 *
 *     @JsonProperty("description")
 *     private String description;
 *
 *     @JsonProperty("priceDetailed")
 *     private Price priceDetailed;
 *
 *     @JsonProperty("geo")
 *     private Geo geo;
 *
 *     public long getId() {
 *         return id;
 *     }
 *
 *     public int getCategoryId() {
 *         return categoryId;
 *     }
 *
 *     public int getLocationId() {
 *         return locationId;
 *     }
 *
 *     public String getTitle() {
 *         return title;
 *     }
 *
 *     public String getDescription() {
 *         return description;
 *     }
 *
 *     public String getPrice() {
 *         return priceDetailed.getFullString();
 *     }
 *
 *     public String getFormattedAddress() {
 *         return geo.getFormattedAddress();
 *     }
 * }
 *
 * class Price {
 *     @JsonProperty("fullString")
 *     private String fullString;
 *
 *     public String getFullString() {
 *         return fullString;
 *     }
 * }
 *
 * class Geo {
 *     @JsonProperty("formattedAddress")
 *     private String formattedAddress;
 *
 *     public String getFormattedAddress() {
 *         return formattedAddress;
 *     }
 * }
 *
 *
 *
 */