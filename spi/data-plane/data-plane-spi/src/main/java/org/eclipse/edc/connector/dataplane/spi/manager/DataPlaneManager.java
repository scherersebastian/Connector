/*
 *  Copyright (c) 2021 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

package org.eclipse.edc.connector.dataplane.spi.manager;

import org.eclipse.edc.connector.dataplane.spi.DataFlowStates;
import org.eclipse.edc.runtime.metamodel.annotation.ExtensionPoint;
import org.eclipse.edc.spi.entity.StateEntityManager;
import org.eclipse.edc.spi.response.StatusResult;
import org.eclipse.edc.spi.result.Result;
import org.eclipse.edc.spi.types.domain.transfer.DataFlowStartMessage;

/**
 * Manages the execution of data plane requests.
 */
@ExtensionPoint
public interface DataPlaneManager extends StateEntityManager {

    /**
     * Determines if the data flow request is valid and can be processed by this runtime.
     */
    Result<Boolean> validate(DataFlowStartMessage dataRequest);

    /**
     * Initiates a transfer for the data flow request. This method is non-blocking with respect to processing the request.
     */
    void initiate(DataFlowStartMessage dataRequest);

    /**
     * Returns the transfer state for the process.
     */
    DataFlowStates transferState(String processId);

    /**
     * Terminate the data flow.
     *
     * @param dataFlowId the data flow id.
     * @return success if data flow is terminated, failed otherwise.
     */
    StatusResult<Void> terminate(String dataFlowId);
}
