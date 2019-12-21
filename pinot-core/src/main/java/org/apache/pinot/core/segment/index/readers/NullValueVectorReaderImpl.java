/**
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
package org.apache.pinot.core.segment.index.readers;

import java.io.IOException;
import org.apache.pinot.core.segment.memory.PinotDataBuffer;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;


public class NullValueVectorReaderImpl implements NullValueVectorReader {

  private final ImmutableRoaringBitmap _nullBitmap;

  public NullValueVectorReaderImpl(PinotDataBuffer nullValueVectorBuffer) throws IOException {
    _nullBitmap = new ImmutableRoaringBitmap(nullValueVectorBuffer.toDirectByteBuffer(0,
        (int) nullValueVectorBuffer.size()));
  }

  public boolean isNull(int docId) {
    return _nullBitmap.contains(docId);
  }

  @Override
  public ImmutableRoaringBitmap getNullBitmap() {
    return _nullBitmap;
  }
}
