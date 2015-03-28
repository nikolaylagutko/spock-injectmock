/**
 * Copyright 2015 Nikolay Lagutko <nikolay.lagutko@mail.com>
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
package org.gerzog.spock.injectmock.internal.accessors;

import org.gerzog.spock.injectmock.injections.IAccessor;

/**
 * @author Nikolay Lagutko (nikolay.lagutko@mail.com)
 *
 */
public abstract class AbstractSingleTypeAccessor implements IAccessor {

	@Override
	public boolean exists(final Class<?> clazz, final String name, final Class<?>... types) {
		return exists(clazz, name, types.length > 0 ? types[0] : null);
	}

	protected abstract boolean exists(final Class<?> clazz, final String name, final Class<?> types);

	@Override
	public Object apply(final Object target, final String name, final Object value) {
		internalSet(target, name, value);

		return value;
	}

	protected abstract void internalSet(final Object target, final String name, final Object value);

}
