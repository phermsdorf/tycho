/*******************************************************************************
 * Copyright (c) 2008, 2011 Sonatype Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sonatype Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.tycho.plugins.p2;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "feature-p2-metadata", threadSafe = true)
public class FeatureP2MetadataMojo extends AbstractP2MetadataMojo {
    private static final Object LOCK = new Object();

    @Override
    protected String getPublisherApplication() {
        return "org.eclipse.equinox.p2.publisher.FeaturesAndBundlesPublisher";
    }

    @Override
    protected void logUpdateSiteLocationNotFound() {
        // this only matters if PackageFeatureMojo#deployableFeature=true
        getLog().debug(getUpdateSiteLocation().getAbsolutePath() + " does not exist or is not a directory");
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        synchronized (LOCK) {
            super.execute();
        }
    }
}
