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

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.spockframework.runtime.InvalidSpecException;

/**
 * @author Nikolay Lagutko (nikolay.lagutko@mail.com)
 *
 */
public class FieldAccessor extends AbstractSingleTypeAccessor {

	@Override
	public boolean exists(final Class<?> clazz, final String name, final Class<?> type) {
		Field field = FieldUtils.getDeclaredField(clazz, name, true);

		boolean result = field != null;

		if (field != null) {
			if (!field.getType().isAssignableFrom(type)) {
				result = false;
			}
		}

		return result;
	}

	@Override
	protected void internalSet(final Object target, final String name, final Object value) {
		try {
			FieldUtils.writeField(target, name, value, true);
		} catch (IllegalAccessException e) {
			throw new InvalidSpecException("Cannot write injectable value to field <" + target.getClass().getSimpleName() + "." + name + ">");
		}
	}

}
