package org.lb.studyelasticsearch.constant;

public class HotelConstant {
    public static final String HOST_URL = "http://192.168.121.140:9200";
    public static final String INDEX_NAME = "hotel";
    public static final String MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\" : {\n" +
            "    \"properties\" : {\n" +
            "      \"id\" : {\n" +
            "        \"type\" : \"keyword\"\n" +
            "      },\n" +
            "      \"name\" : {\n" +
            "        \"type\" : \"text\",\n" +
            "        \"analyzer\" : \"ik_max_word\",\n" +
            "        \"copy_to\" : \"all\"\n" +
            "      },\n" +
            "      \"address\" : {\n" +
            "        \"type\" : \"keyword\",\n" +
            "        \"index\" : false\n" +
            "      },\n" +
            "      \"priee\" : {\n" +
            "        \"type\" : \"integer\"\n" +
            "      },\n" +
            "      \"score\" : {\n" +
            "        \"type\" : \"integer\"\n" +
            "      },\n" +
            "      \"brand\" : {\n" +
            "        \"type\" : \"keyword\",\n" +
            "        \"copy_to\" : \"all\"\n" +
            "        \n" +
            "      },\n" +
            "      \"city\": {\n" +
            "        \"type\" : \"keyword\"\n" +
            "      },\n" +
            "      \"starName\" : {\n" +
            "        \"type\" : \"keyword\"\n" +
            "      },\n" +
            "      \"business\" : {\n" +
            "        \"type\" : \"keyword\",\n" +
            "        \"copy_to\" : \"all\"\n" +
            "      },\n" +
            "      \"location\" : {\n" +
            "        \"type\" : \"geo_point\"\n" +
            "      },\n" +
            "      \"pic\" : {\n" +
            "        \"type\" : \"keyword\",\n" +
            "        \"index\" : false\n" +
            "      },\n" +
            "      \"all\" : {\n" +
            "        \"type\" : \"text\",\n" +
            "        \"analyzer\" : \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
