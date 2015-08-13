/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.treasuredata.client;

import com.google.common.base.Joiner;
import com.treasuredata.client.api.model.TDJob;
import com.treasuredata.client.api.model.TDJobList;
import com.treasuredata.client.api.model.TDJobRequest;
import com.treasuredata.client.api.model.TDJobStatus;
import com.treasuredata.client.api.model.TDTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 */
public class TestTDClient
{
    private static final Logger logger = LoggerFactory.getLogger(TestTDClient.class);
    private TDClient client;

    @Before
    public void setUp()
            throws Exception
    {
        client = new TDClient();
    }

    @After
    public void tearDown()
            throws Exception
    {
        client.close();
    }

    @Test
    public void listDatabases()
            throws Exception
    {
        List<String> dbList = client.listDatabases();
        assertTrue("should contain sample_datasets", dbList.contains("sample_datasets"));

        logger.debug(Joiner.on(", ").join(dbList));
    }

    @Test
    public void listTables()
            throws Exception
    {
        List<TDTable> tableList = client.listTables("sample_datasets");
        assertTrue(tableList.size() >= 2);
        logger.debug(Joiner.on(", ").join(tableList));

        for (TDTable t : tableList) {
            if (t.getName().equals("nasdaq")) {
                assertTrue(t.getColumns().size() == 6);
            }
            else if (t.getName().equals("www_access")) {
                assertTrue(t.getColumns().size() == 8);
            }
        }
    }

    @Test
    public void listJobs()
            throws Exception
    {
        TDJobList jobs = client.listJobs();
        logger.debug("job list: " + jobs);
    }

    @Test
    public void submitJob()
            throws Exception
    {
        TDJobStatus jobResult = client.submit(TDJobRequest.newPrestoQuery("sample_datasets", "select count(*) from nasdaq"));
        logger.debug("job: " + jobResult);

        TDJob tdJob = client.jobStatus(jobResult.getJobId());
        logger.debug("job status: " + tdJob);
    }

    @Test
    public void invalidJobStatus() {
        try {
            TDJob invalidJob = client.jobStatus("xxxxxx");
            logger.debug("invalid job: " + invalidJob);

            fail("should not reach here");
        }
        catch(TDClientException e) {

        }
    }

    private static String SAMPLE_DB = "_tdclient_test";
    private static String SAMPLE_TABLE = "sample";

    @Test
    public void createAndDeleteDatabase()
            throws Exception
    {
        if(client.existsDatabase(SAMPLE_DB)) {
            client.createDatabase(SAMPLE_DB);
        }
        client.deleteDatabase(SAMPLE_DB);
    }

    @Test
    public void createTable()
            throws Exception
    {
        client.createDatabase(SAMPLE_DB);
        client.createTable(SAMPLE_DB, SAMPLE_TABLE);
        client.deleteTable(SAMPLE_DB, SAMPLE_TABLE);
    }
}