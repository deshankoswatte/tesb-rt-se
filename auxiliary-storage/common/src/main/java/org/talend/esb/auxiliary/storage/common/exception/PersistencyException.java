/*
 * ============================================================================
 *
 * Copyright (C) 2011-2020 Talend Inc. - www.talend.com
 *
 * This source code is available under agreement available at
 * %InstallDIR%\license.txt
 *
 * You should have received a copy of the agreement
 * along with this program; if not, write to Talend SA
 * 9 rue Pages 92150 Suresnes, France
 *
 * ============================================================================
 */
package org.talend.esb.auxiliary.storage.common.exception;

public class PersistencyException extends AuxiliaryStorageException {

    /**
     *
     */
    private static final long serialVersionUID = 7871176774549240702L;

    public PersistencyException(String message) {
        super(message);
    }

    public PersistencyException(String message, Throwable e) {
        super(message, e);
    }

}
