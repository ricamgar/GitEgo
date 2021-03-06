/*
 * Copyright (c) 2016. Héctor de Isidro - hrules6872
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hrules.gitego.data.repository.datasources;

import android.support.annotation.NonNull;
import com.hrules.gitego.data.repository.base.RepositoryInterface;
import com.hrules.gitego.domain.models.base.ModelDto;
import com.hrules.gitego.domain.specifications.base.Specification;
import java.util.Collection;

public abstract class DataSource<T extends ModelDto> implements RepositoryInterface<T> {
  public abstract Collection<T> query(@NonNull Specification specification) throws Exception;

  public String getSpecificationsPath() {
    return getClass().getPackage().getName() + ".specifications";
  }

  public String getSpecificationsPrefix() {
    return getClass().getSimpleName().replace("DataSource", "");
  }

  public abstract boolean isReadable();

  public abstract boolean isWriteable();

  public abstract boolean isCacheExpired();
}

