/**
 *
 * Copyright © 2014-2015 Florian Schmaus
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
package org.jxmpp.jid.impl;

import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.DomainBareJid;
import org.jxmpp.jid.DomainFullJid;
import org.jxmpp.jid.FullJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.JidWithLocalpart;
import org.jxmpp.jid.JidWithResource;
import org.jxmpp.jid.parts.Domainpart;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.jid.parts.Resourcepart;
import org.jxmpp.stringprep.XmppStringprepException;

public final class LocalDomainAndResourcepartJid extends AbstractJid implements FullJid {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final BareJid bareJid;
	private final Resourcepart resource;

	private String cache;
	private String unescapedCache;

	LocalDomainAndResourcepartJid(String localpart, String domain, String resource) throws XmppStringprepException {
		this(new LocalAndDomainpartJid(localpart, domain), Resourcepart.from(resource));
	}

	LocalDomainAndResourcepartJid(BareJid bareJid, Resourcepart resource) {
		this.bareJid = bareJid;
		this.resource = resource;
	}

	public final Resourcepart getResource() {
		return resource;
	}

	@Override
	public String toString() {
		if (cache != null) {
			return cache;
		}
		cache = bareJid.toString() + '/' + resource;
		return cache;
	}

	@Override
	public String asUnescapedString() {
		if (unescapedCache != null) {
			return unescapedCache;
		}
		unescapedCache = bareJid.asUnescapedString() + '/' + resource;
		return unescapedCache;
	}

	@Override
	public BareJid asBareJid() {
		return bareJid;
	}

	@Override
	public String asBareJidString() {
		return asBareJid().toString();
	}

	@Override
	public final boolean hasNoResource() {
		return false;
	}

	@Override
	public BareJid asBareJidIfPossible() {
		return asBareJid();
	}

	@Override
	public FullJid asFullJidIfPossible() {
		return this;
	}

	@Override
	public DomainBareJid asDomainBareJidIfPossible() {
		return null;
	}

	@Override
	public DomainFullJid asDomainFullJidIfPossible() {
		return null;
	}

	@Override
	public Localpart getLocalpartOrNull() {
		return bareJid.getLocalpart();
	}

	@Override
	public Resourcepart getResourceOrNull() {
		return getResource();
	}

	@Override
	public boolean isParentOf(BareJid bareJid) {
		return false;
	}

	@Override
	public boolean isParentOf(FullJid fullJid) {
		return this.equals(fullJid);
	}

	@Override
	public boolean isParentOf(DomainBareJid domainBareJid) {
		return false;
	}

	@Override
	public boolean isParentOf(DomainFullJid domainFullJid) {
		return false;
	}

	@Override
	public DomainBareJid asDomainBareJid() {
		return bareJid.asDomainBareJid();
	}

	@Override
	public Resourcepart getResourcepart() {
		return resource;
	}

	@Override
	public Jid withoutResource() {
		return asBareJid();
	}

	@Override
	public Domainpart getDomain() {
		return bareJid.getDomain();
	}

	@Override
	public Localpart getLocalpart() {
		return bareJid.getLocalpart();
	}

	@Override
	public JidWithLocalpart asJidWithLocalpartIfPossible() {
		return this;
	}

	@Override
	public JidWithResource asJidWithResourcepartIfPossible() {
		return this;
	}
}
