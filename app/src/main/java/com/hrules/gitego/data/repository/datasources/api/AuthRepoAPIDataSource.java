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

package com.hrules.gitego.data.repository.datasources.api;

import android.support.annotation.NonNull;
import com.hrules.gitego.data.network.Network;
import com.hrules.gitego.data.network.RequestNetwork;
import com.hrules.gitego.data.persistence.database.utils.DatabaseDateUtils;
import com.hrules.gitego.data.repository.cache.base.BasicCache;
import com.hrules.gitego.data.repository.datasources.DataSource;
import com.hrules.gitego.data.repository.datasources.api.specifications.AuthRepoSubscribersAPIGetAuthRepoSubscribersSpecification;
import com.hrules.gitego.domain.models.GitHubAuthRepoDto;
import com.hrules.gitego.domain.models.serializers.GitHubAuthRepoDtoSerializer;
import com.hrules.gitego.domain.specifications.base.Specification;
import com.hrules.gitego.domain.specifications.base.SpecificationFactory;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;

public class AuthRepoAPIDataSource extends DataSource<GitHubAuthRepoDto> {
  private final Network network;
  private final BasicCache cache;

  public AuthRepoAPIDataSource(Network network, BasicCache cache) {
    this.network = network;
    this.cache = cache;
  }

  @Override public void addOrUpdate(@NonNull GitHubAuthRepoDto item) throws Exception {
    throw new UnsupportedOperationException();
  }

  @Override public void addOrUpdate(@NonNull Iterable<GitHubAuthRepoDto> items) throws Exception {
    throw new UnsupportedOperationException();
  }

  @Override public void remove(@NonNull GitHubAuthRepoDto item) throws Exception {
    throw new UnsupportedOperationException();
  }

  @Override public void remove(@NonNull Iterable<GitHubAuthRepoDto> items) throws Exception {
    throw new UnsupportedOperationException();
  }

  @Override public void remove(@NonNull Specification specification) throws Exception {
    throw new UnsupportedOperationException();
  }

  @Override
  public void query(@NonNull Specification specification, @NonNull QueryCallback callback) {
    throw new UnsupportedOperationException();
  }

  @SuppressWarnings("unchecked") @Override
  public Collection<GitHubAuthRepoDto> query(@NonNull Specification specification)
      throws Exception {
    specification = new SpecificationFactory<String>().get(this, specification);
    List<GitHubAuthRepoDto> list;

    String response = network.get((RequestNetwork) specification.get());
    list = (List<GitHubAuthRepoDto>) new GitHubAuthRepoDtoSerializer().deserialize(response);
    for (GitHubAuthRepoDto item : list) {
      item.setDate(DatabaseDateUtils.formatDateToSQLShort(System.currentTimeMillis()));
      item.setModelId(item.createModelId());

      Specification subscribersSpecification =
          new AuthRepoSubscribersAPIGetAuthRepoSubscribersSpecification();
      subscribersSpecification.setAdditionalParams(specification.getAdditionalParams()[0],
          item.getSubscribers_url());
      String subscribers = network.get((RequestNetwork) subscribersSpecification.get());
      JSONArray subscribersJSONArray = new JSONArray(subscribers);
      item.setWatchers_count(subscribersJSONArray.length());
    }
    cache.persist();
    return list;
  }

  @Override public boolean isReadable() {
    return true;
  }

  @Override public boolean isWriteable() {
    return false;
  }

  @Override public boolean isCacheExpired() {
    return cache.isExpired();
  }
}
