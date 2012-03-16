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
package com.treasure_data.model;

public class JobSummary extends Job {
    private Database database;

    private String url;

    private Status status;

    private String createdAt;

    private String startAt;

    private String endAt;

    private String query;

    public Job(String jobId, Type jobType) {
        super(jobType, jobType);
    }

    public Database getDatabase() {
        return database;
    }

    public String getURL() {
        return url;
    }

    public Status getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // TODO String -> kind of time type
    public String getStartAt() {
        return startAt;
    }

    // TODO String -> kind of time type
    public String getEndAt() {
        return endAt;
    }

    public String getQuery() {
        return query;
    }

    // This feature will be removed soon
    //public String getResultTable() {
    //    return resultTable;
    //}

    // TODO setXxx
}

