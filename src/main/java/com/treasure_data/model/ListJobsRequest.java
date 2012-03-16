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

public class ListJobsRequest extends AbstractRequest {
    private long offset;
    private int limit;

    public ListJobsRequest() {
    }

    public void getOffset() {
        return offset;
    }

    public void getLimit() {
        return limit;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ListJobsRequest withOffset(long offset) {
        ListJobsRequest c = (ListJobsRequest) clone();
        c.setOffset(offset);
        return c;
    }

    public ListJobsRequest withLimit(long limit) {
        ListJobsRequest c = (ListJobsRequest) clone();
        c.setLimit(limit);
        return c;
    }
}
