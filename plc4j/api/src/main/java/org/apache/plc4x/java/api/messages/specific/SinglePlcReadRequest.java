/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package org.apache.plc4x.java.api.messages.specific;

import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.items.ReadRequestItem;
import org.apache.plc4x.java.api.model.Address;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SinglePlcReadRequest<T> implements PlcReadRequest {

    private ReadRequestItem<T> readRequestItem;

    public SinglePlcReadRequest() {
    }

    public SinglePlcReadRequest(Class<T> dataType, Address address) {
        readRequestItem = new ReadRequestItem<>(dataType, address);
    }

    public SinglePlcReadRequest(Class<T> dataType, Address address, int size) {
        readRequestItem = new ReadRequestItem<>(dataType, address, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addItem(ReadRequestItem readRequestItem) {
        if (this.readRequestItem != null && readRequestItem != null) {
            throw new IllegalStateException(SinglePlcReadRequest.class.getName() + " can only contain on readRequestItem");
        }
        this.readRequestItem = (ReadRequestItem<T>) readRequestItem;
    }

    @Override
    public List<ReadRequestItem<T>> getReadRequestItems() {
        return readRequestItem != null ? Collections.singletonList(readRequestItem) : Collections.emptyList();
    }

    public Optional<ReadRequestItem<T>> getReadRequestItem() {
        return Optional.of(readRequestItem);
    }

    public void setReadRequestItem(ReadRequestItem<T> readRequestItem) {
        this.readRequestItem = readRequestItem;
    }

    public int getNumberOfItems() {
        return readRequestItem != null ? 1 : 0;
    }
}