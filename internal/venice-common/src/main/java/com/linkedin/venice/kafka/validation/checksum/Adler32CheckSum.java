/*
 * Copyright 2008-2009 LinkedIn, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.linkedin.venice.kafka.validation.checksum;

import com.linkedin.venice.utils.ByteUtils;
import java.util.zip.Adler32;


/**
 * Running checksum implementations based on Adler32.
 *
 * N.B.: Class taken from Voldemort.
 */
public class Adler32CheckSum extends CheckSum {
  private Adler32 checkSumGenerator = null;

  public Adler32CheckSum() {
    checkSumGenerator = new Adler32();
  }

  @Override
  public byte[] getFinalCheckSum() {
    byte[] returnedCheckSum = new byte[ByteUtils.SIZE_OF_LONG];
    ByteUtils.writeLong(returnedCheckSum, checkSumGenerator.getValue(), 0);
    return returnedCheckSum;
  }

  @Override
  public void updateChecksum(byte[] input, int startIndex, int length) {
    checkSumGenerator.update(input, startIndex, length);

  }

  @Override
  public void resetInternal() {
    checkSumGenerator.reset();
  }

  @Override
  public CheckSumType getType() {
    return CheckSumType.ADLER32;
  }
}
