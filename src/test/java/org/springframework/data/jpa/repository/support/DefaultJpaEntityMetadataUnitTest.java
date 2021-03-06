/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.jpa.repository.support;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.persistence.Entity;

import org.junit.Test;

/**
 * Unit tests for {@link DefaultJpaEntityMetadata}.
 * 
 * @author Oliver Gierke
 */
public class DefaultJpaEntityMetadataUnitTest {

	@Test(expected = IllegalArgumentException.class)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void rejectsNullDomainType() {
		new DefaultJpaEntityMetadata(null);
	}

	@Test
	public void returnsConfiguredType() {

		DefaultJpaEntityMetadata<Foo> metadata = new DefaultJpaEntityMetadata<Foo>(Foo.class);
		assertThat(metadata.getJavaType(), is(equalTo(Foo.class)));
	}

	@Test
	public void returnsSimpleClassNameAsEntityNameByDefault() {

		DefaultJpaEntityMetadata<Foo> metadata = new DefaultJpaEntityMetadata<Foo>(Foo.class);
		assertThat(metadata.getEntityName(), is(Foo.class.getSimpleName()));
	}

	@Test
	public void returnsCustomizedEntityNameIfConfigured() {

		DefaultJpaEntityMetadata<Bar> metadata = new DefaultJpaEntityMetadata<Bar>(Bar.class);
		assertThat(metadata.getEntityName(), is("Entity"));
	}

	static class Foo {}

	@Entity(name = "Entity")
	static class Bar {}
}
