/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package suite;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ibm.ws.transaction.fat.util.TxTestContainerSuite;

import componenttest.containers.SimpleLogConsumer;
import componenttest.rules.repeater.FeatureReplacementAction;
import componenttest.rules.repeater.RepeatTests;
import componenttest.topology.database.container.DatabaseContainerType;
import componenttest.topology.database.container.PostgreSQLContainer;
import tests.FailoverTestRetriableCodes;

@RunWith(Suite.class)
@SuiteClasses({ FailoverTestRetriableCodes.class })
public class FATSuite extends TxTestContainerSuite {
    private static final String POSTGRES_DB = "testdb";
    private static final String POSTGRES_USER = "postgresUser";
    private static final String POSTGRES_PASS = "superSecret";

    static {
        databaseContainerType = DatabaseContainerType.Postgres;

        /*
         * The image here is generated using the Dockerfile in com.ibm.ws.jdbc_fat_postgresql/publish/files/postgresql-ssl
         * The command used in that directory was: docker build -t jonhawkes/postgresql-ssl:1.0 .
         * With the resulting image being pushed to docker hub.
         */
        testContainer = new PostgreSQLContainer("jonhawkes/postgresql-ssl:1.0")
                        .withDatabaseName(POSTGRES_DB)
                        .withUsername(POSTGRES_USER)
                        .withPassword(POSTGRES_PASS)
                        .withSSL()
                        .withLogConsumer(new SimpleLogConsumer(FATSuite.class, "postgre-ssl"));
    }

    @ClassRule
    public static RepeatTests r = RepeatTests.withoutModification()
                    .andWith(FeatureReplacementAction.EE8_FEATURES().fullFATOnly().forServers(FailoverTestRetriableCodes.serverNames))
                    .andWith(FeatureReplacementAction.EE9_FEATURES().fullFATOnly().forServers(FailoverTestRetriableCodes.serverNames))
                    .andWith(FeatureReplacementAction.EE10_FEATURES().fullFATOnly().forServers(FailoverTestRetriableCodes.serverNames));
}