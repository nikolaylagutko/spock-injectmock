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
package org.gerzog.spock.injectmock.internal.injectables;

import org.spockframework.runtime.GroovyRuntimeUtil;
import org.spockframework.runtime.InvalidSpecException;
import org.spockframework.runtime.model.FieldInfo;

/**
 * @author Nikolay Lagutko (nikolay.lagutko@mail.com)
 *
 */
class AbstractAnnotatedInjectable extends AbstractInjectable {

	private final String instantiationMethodName;

	protected AbstractAnnotatedInjectable(final FieldInfo fieldInfo, final String instantiationMethodName) {
		super(fieldInfo);

		this.instantiationMethodName = instantiationMethodName;
	}

	@Override
	public Object instantiate(final Object target) {
		Object result = super.instantiate(target);

		if (result != null) {
			throw new InvalidSpecException("Field <" + getName() + "> already have value and cannot be re-initialized.");
		}

		result = GroovyRuntimeUtil.invokeMethod(target, instantiationMethodName, getName(), getType());

		return result;
	}
}
