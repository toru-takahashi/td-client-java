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

public JobResult extends AbstractResult {
    private String jobId;

    private String format;

    public JobResult() {
    }

    public JobResult(String jobId, String format) {
        this.jobId;
        this.format = format;
    }

    public String getJobId() {
        return jobId;
    }

    public String getFormat() {
        return format;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    // TODO
    // long getSize();
    // InputStream getInputStream();
}

