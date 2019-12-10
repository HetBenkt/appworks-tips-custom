package com.appworkstips.utils;

public interface IConstants {
    String SAMPLE_JSON_RESULT = "{\"passwordExpirationTime\":0,\"resourceID\":\"5dae6e94-f382-47d5-ba5e-289455e72604\",\"ticket\":\"*VER2*ABTle0_50wJHgDpsif1OaFIajNMCSwAQ-OlvHocu1BT0mxjGy4QrEQMgAToAejoCnozPupKyL_4ygcguCVNMwZGLRgZB9TsyY8zSEwhLzQWlklDN-mzYdbU9ywkw8BOKdNTA1PXWuFCuuYzFncgy_9hP5_HUH53_b4HchzVNbVnlTruGGJ3dzWbVaMp-F49mIK2Ryt3Fg5iGy_75hO6AENOXYNEkMngkQu60Zh_Sd-liVmjuERsjCLyV9iQIYiX69Wql_mgDgCQUzLSSp6AS7ELRWCSQDlF1Fb76piS-Qs6GAHroY4DcdIFUxSL5MFXqZGonM86VbwdL0dNk8Cl8_LURqfXS3ojr7nufcszH8o4bQ5j2qO7CCnfs61V6WmXDCFVeA10zBCKpLX3t2QiXTNFW9nElQox_oA58IS6-84LhDnOYAxiGNjjkLNoTkyBV4u8aNslVZ3yQL4K1xs1ofIjsLcQ47co8vZMqeVGuOzogADKFoAob6ZyWe_bNFLR2w3sG3G3EHEQulzPe3GWG-fOsRIADttBNEHY2OOo0AQnBoltcjbXO4YOtqK6JH8k7HlysUufV8tjX6DfA-D9vhj7rPfl4cS93pJhdPuh44CU5L9HRLGTAZh5D-2iBS3sdd_2qwqY9WYy-ol6ntGUi-I2FKkTw_ricLmJUh5Zy3hIkTXLvkqHwOffJgjNa4nDrIWLaTTAzBcFM1i52a1W3vxevPHyeOLC_b3fGcUVTA5vMH8pDFrJ7qYBl5xUxfcvFafgnxAS2zOD34odrs6EtKMizqNOt695es8XamSQ0FBCO1kp3AHKxUVv_AGXT8UZhhed10A-Fx9WahJ-KRYzaTKaG99pYMhHDhdJyjIEcA4dTJzFeB7vgRqE7Ag7_58I4VXMr_mq_5XJKxD5nbDnXvk9esQNW2V68k6eONhKyPH64S64liBjPBJ1zVhMN1d3dUz7cub7QgpxcHFE3VLQezYQX6vReI0rv6_B4V2rrXEZDB9Vxysb9rMwtbGtS6Kt2X3a-RSt5LILdD58Pq9le2lW7Oek-cXZ-Im4q5LBXasbz72_K5oEAeQky_stL5UHLwTEEoqnnsmJDAA9iIIlkIuRgfnB7eaav6Z0*\",\"failureReason\":null,\"continuationData\":null,\"userId\":\"otdsdev@AppWorks Platform Partition\",\"continuation\":false,\"continuationContext\":null,\"token\":\"6F7464735F73657373696F6E5F6B6579\"}";

    String SAMPLE_RANDOM_INT_SOAP_RESULT = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:app=\"http://schemas.cordys.com/AppWorksServices\"><SOAP-ENV:Header xmlns:app=\"http://schemas.cordys.com/AppWorksServices\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><header xmlns:app=\"http://schemas.cordys.com/AppWorksServices\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://schemas.cordys.com/General/1.0/\"><msg-id>0800270c-e1be-a1e9-be93-5ef6d9da2d19</msg-id><messageoptions noreply=\"true\"/></header></SOAP-ENV:Header><SOAP-ENV:Body><getRandomIntValueMinMaxResponse xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://schemas.cordys.com/AppWorksServices\"><tuple><old><getRandomIntValueMinMax><getRandomIntValueMinMax>73</getRandomIntValueMinMax></getRandomIntValueMinMax></old></tuple></getRandomIntValueMinMaxResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
    String SAMPLE_ALL_USERS_SOAP_RESULT = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://schemas.cordys.com/General/1.0/\"><msg-id>08002769-07f1-a1ea-82af-bc9192b83055</msg-id><messageoptions noreply=\"true\"/></header></SOAP-ENV:Header><SOAP-ENV:Body><wstxns1:GetAllUsersResponse xmlns:wstxns1=\"http://schemas/OpenTextEntityIdentityComponents/User/operations\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><wstxns2:User xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:wstxns1=\"http://schemas/OpenTextEntityIdentityComponents/User/operations\" xmlns:wstxns2=\"http://schemas/OpenTextEntityIdentityComponents/User\" xmlns=\"http://schemas/OpenTextEntityIdentityComponents/User\"><wstxns3:Name xmlns:wstxns3=\"http://schemas/OpenTextEntityIdentityComponents/Identity\">awadmin</wstxns3:Name><FullName>AppWorks Administrator</FullName><wstxns4:IdentityDisplayName xmlns:wstxns4=\"http://schemas/OpenTextEntityIdentityComponents/Identity\" xsi:nil=\"true\"/><wstxns5:Description xmlns:wstxns5=\"http://schemas/OpenTextEntityIdentityComponents/Identity\" xsi:nil=\"true\"/><UserId>awadmin</UserId><memberid xsi:nil=\"true\"/><wstxns6:Identity-id xmlns:wstxns6=\"http://schemas/OpenTextEntityIdentityComponents/Identity\" xmlns=\"http://schemas/OpenTextEntityIdentityComponents/Identity\"><Id>2</Id><ItemId>F8B156E1037111E6E9CB0FBF3334FBBF.2</ItemId></wstxns6:Identity-id><wstxns7:Title xmlns:wstxns7=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\" xmlns=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\"><Value>awadmin</Value></wstxns7:Title></wstxns2:User></wstxns1:GetAllUsersResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
    String SAMPLE_CREATE_CATEGORY_ENTITY_RESULT = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://schemas.cordys.com/General/1.0/\"><msg-id>08002769-07f1-a1ea-83dc-21882d05a3f9</msg-id><messageoptions noreply=\"true\"/></header></SOAP-ENV:Header><SOAP-ENV:Body><wstxns1:CreatecategoryResponse xmlns:wstxns1=\"http://schemas/AppWorksTipsAppWorks/category/operations\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><wstxns2:category xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:wstxns1=\"http://schemas/AppWorksTipsAppWorks/category/operations\" xmlns:wstxns2=\"http://schemas/AppWorksTipsAppWorks/category\" xmlns=\"http://schemas/AppWorksTipsAppWorks/category\"><category-id><Id>983043</Id><ItemId>0800276907f1a1ea825c64f7cdc2116a.983043</ItemId></category-id><cat_id>ID_983043</cat_id><cat_is_enabled>true</cat_is_enabled><cat_name>myName</cat_name><cat_description>myDescription</cat_description><wstxns3:Title xmlns:wstxns3=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\" xmlns=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\"><Value>Title_983043</Value></wstxns3:Title></wstxns2:category></wstxns1:CreatecategoryResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
    String SAMPLE_READ_CATEGORY_ENTITY_RESULT = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://schemas.cordys.com/General/1.0/\"><msg-id>08002769-07f1-a1ea-83dc-21882d05a3f9</msg-id><messageoptions noreply=\"true\"/></header></SOAP-ENV:Header><SOAP-ENV:Body><wstxns1:ReadcategoryResponse xmlns:wstxns1=\"http://schemas/AppWorksTipsAppWorks/category/operations\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><wstxns2:category xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:wstxns1=\"http://schemas/AppWorksTipsAppWorks/category/operations\" xmlns:wstxns2=\"http://schemas/AppWorksTipsAppWorks/category\" xmlns=\"http://schemas/AppWorksTipsAppWorks/category\"><category-id><Id>655361</Id><ItemId>0800276907f1a1ea825c64f7cdc2116a.655361</ItemId></category-id><cat_id>ID_655361</cat_id><cat_is_enabled>false</cat_is_enabled><cat_name xsi:nil=\"true\" /><cat_description xsi:nil=\"true\" /><cat_priority xsi:nil=\"true\" /><cat_type xsi:nil=\"true\" /><wstxns3:Title xmlns:wstxns3=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\" xmlns=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\"><Value>Title_655361</Value></wstxns3:Title></wstxns2:category></wstxns1:ReadcategoryResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
    String SAMPLE_DELETE_CATEGORY_ENTITY_RESULT = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://schemas.cordys.com/General/1.0/\"><msg-id>08002769-07f1-a1ea-83dc-21882d05a3f9</msg-id><messageoptions noreply=\"true\"/></header></SOAP-ENV:Header><SOAP-ENV:Body><wstxns1:DeletecategoryResponse xmlns:wstxns1=\"http://schemas/AppWorksTipsAppWorks/category/operations\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" /></SOAP-ENV:Body></SOAP-ENV:Envelope>";
    String SAMPLE_ALL_CATEGORY_ENTITY_RESULT = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://schemas.cordys.com/General/1.0/\"><msg-id>08002769-07f1-a1ea-83dc-21882d05a3f9</msg-id><messageoptions noreply=\"true\"/></header></SOAP-ENV:Header><SOAP-ENV:Body>  <wstxns1:AllCategoriesResponse xmlns:wstxns1=\"http://schemas/AppWorksTipsAppWorks/category/operations\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><wstxns2:category xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:wstxns1=\"http://schemas/AppWorksTipsAppWorks/category/operations\" xmlns:wstxns2=\"http://schemas/AppWorksTipsAppWorks/category\" xmlns=\"http://schemas/AppWorksTipsAppWorks/category\"><category-id><Id>2</Id><ItemId>0800276907f1a1ea825c64f7cdc2116a.2</ItemId></category-id><cat_id>ID_2</cat_id><cat_is_enabled>true</cat_is_enabled><cat_name>asdfasdf</cat_name><cat_description>DONE</cat_description><cat_priority>1</cat_priority><cat_type>bug</cat_type><wstxns3:Title xmlns:wstxns3=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\" xmlns=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\"><Value>Title_2</Value></wstxns3:Title></wstxns2:category><wstxns4:category xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:wstxns1=\"http://schemas/AppWorksTipsAppWorks/category/operations\" xmlns:wstxns4=\"http://schemas/AppWorksTipsAppWorks/category\" xmlns=\"http://schemas/AppWorksTipsAppWorks/category\"><category-id><Id>344065</Id><ItemId>0800276907f1a1ea825c64f7cdc2116a.344065</ItemId></category-id><cat_id>ID_344065</cat_id><cat_is_enabled>false</cat_is_enabled><cat_name xsi:nil=\"true\" /><cat_description xsi:nil=\"true\" /><cat_priority xsi:nil=\"true\" /><cat_type xsi:nil=\"true\" /><wstxns5:Title xmlns:wstxns5=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\" xmlns=\"http://schemas.opentext.com/entitymodeling/buildingblocks/title/1.0\"><Value>Title_344065</Value></wstxns5:Title></wstxns4:category></wstxns1:AllCategoriesResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
}
