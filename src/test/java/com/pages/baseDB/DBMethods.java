package com.pages.baseDB;

import java.io.IOException;

public class DBMethods extends DBConnections {
	
    public DBMethods() throws IOException {
		super();
	}

	/**
     * @Performs
     *      Fetches the Authorization Token from DB for loan creation.
     * @param entityID
     * @return
     * @throws IOException
     */
    public String getAuthorizationToken(int entityID) throws IOException {
            String token=getValueWithColumnName("select t1.token\n" +
                    "            from KWDB108.AFF_TOKMST t1\n" +
                    "           inner join KWDB108.token_source t2\n" +
                    "              on t1.sourceid = T2.sourceid\n" +
                    "           where t1.dealernumber = '"+entityID+"'\n" +
                    "             and t1.dealerlocation = 1\n" +
                    "          and t1.activeflag = 'Y'\n" +
                    "          and t2.sourcename = 'DP'","TOKEN");
            return token;
        }
}
