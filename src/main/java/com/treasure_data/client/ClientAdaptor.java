//
// Java Client Library for Treasure Data Cloud
//
// Copyright (C) 2011 - 2012 Muga Nishizawa
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
package com.treasure_data.client;


public interface TreasureDataClientAdaptor {
    // Server Status API

    ServerStatus getServerStatus(ServerStatusRequest request) throws ClientException;

    // Database API

    ListDatabasesResult listDatabases(ListDatabasesRequest request) throws ClientException;

    List<Database> listDatabases() throws ClientException;

    Database createDatabase(CreateDatabaseRequest request) throws ClientException;

    Database createDatabase(String databaseName) throws ClientException;

    void deleteDatabase(DeleteDatabaseRequest request) throws ClientException;

    void deleteDatabase(String databaseName) throws ClientException;

    // Table API

    ListTablesResult listTables(ListTablesRequest request) throws ClientException; // array of TableSummary < Table

    List<TableSummary> listTables(String databaseName) throws ClientException {
        return listTables(new ListTablesRequest(databaseName)).getTableSummaries();
    }

    Table createTable(CreateTableRequest request) throws ClientException;

    Table createTable(String databaseName, String tableName) throws ClientException;

    TableDescription describeTable(DescribeTableRequest request) throws ClientException;  // TableDescription < TableSummary

    void deleteTable(DeleteTableRequest request) throws ClientException;

    //TODO TableSchema updateTableSchema(UpdateTableSchemaRequest request) throws ClientException;

    // Import API

    ImportDataResult importData(ImportDataRequest request) throws ClientException;

    // Export API

    ExportDataResult exportData(ExportDataRequest request) throws ClientException; // Job

    // Job API

    ListJobsResult listJobs(ListJobsRequest request) throws ClientException; // array of JobSummary < Job

    Job submitJob(SubmitJobRequest request) throws ClientException; // Job

    KillJobResult killJob(KillJobRequest request) throws ClientException;

    JobDescription describeJob(DescribeJobRequest request) throws ClientException; // JobDescription < JobSummary

    JobResult getJobResult(GetJobResultRequest request) throws ClientException;

    // Job scheduling API

    ListScheduledJobResult listJobSchedules(ListScheduledJobRequest request) throws ClientException;  // JobScheduleSummary < JobSchedule

    List<JobSchedule> listJobSchedules() throws ClientException;

    JobSchedule createJobSchedule(CreateJobScheduleRequest request) throws ClientException;

    void deleteJobSchedule(DeleteJobScheduleRequest request) throws ClientException;
}

