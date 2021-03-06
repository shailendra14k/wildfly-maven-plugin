/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.wildfly.plugin.server;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.wildfly.plugin.deployment.DeploymentException;

/**
 * A helper for interacting with deployments on a managed domain server.
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public interface DomainDeploymentManager extends DeploymentManager {

    /**
     * Gets all the deployments.
     *
     * @param serverGroup the server group to get deployments for
     *
     * @return a set of the deployment names
     *
     * @throws IOException if an error occurs communicating with the server
     */
    Set<String> getDeployments(String serverGroup) throws IOException;

    /**
     * Checks to see if the deployment exists on the running server.
     *
     * @param deploymentName the name of the deployment
     * @param serverGroup    the server group to check if the deployment exists on
     *
     * @return {@code true} if the deployment exists, otherwise {@code false}
     *
     * @throws IOException      if an error occurs communicating with the server
     * @throws RuntimeException if an error occurs checking the deployment
     */
    boolean isDeployed(String deploymentName, String serverGroup) throws IOException;

    /**
     * Attempts to deploy the content to the server.
     *
     * @param deploymentName the name used for the deployment on the server
     * @param serverGroups   the server groups to deploy to
     * @param content        the content to deploy
     *
     * @throws IOException         if an error occurs communicating with the server
     * @throws DeploymentException if the deployment execution fails
     * @throws RuntimeException    if an error occurs deploying the content
     */
    void deploy(String deploymentName, Collection<String> serverGroups, File content) throws IOException, DeploymentException;

    /**
     * Attempts to undeploy from a server.
     *
     * @param deploymentName the name of the deployment to remove
     * @param serverGroups   the server groups to undeploy to
     *
     * @throws IOException         if an error occurs communicating with the server
     * @throws DeploymentException if the deployment execution fails
     * @throws RuntimeException    if an error occurs removing the deployment
     */
    void undeploy(String deploymentName, Collection<String> serverGroups) throws IOException, DeploymentException;
}
