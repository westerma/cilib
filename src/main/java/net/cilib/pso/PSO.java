/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.cilib.pso;

import com.google.inject.Inject;
import net.cilib.algorithm.PopulationBasedAlgorithm;
import net.cilib.collection.Topology;
import net.cilib.entity.CandidateSolution;
import net.cilib.entity.ParticleProvider;
import net.cilib.entity.MutableSeq;
import net.cilib.entity.Particle;
import net.cilib.entity.Velocity;

/**
 * @since 0.8
 * @author gpampara
 */
public class PSO implements PopulationBasedAlgorithm<Particle> {

    private final VelocityProvider velocityProvider;
    private final ParticleProvider particleProvider;

    /**
     *
     * @param velocityProvider
     */
    @Inject
    public PSO(VelocityProvider velocityProvider, ParticleProvider particleProvider) {
        this.velocityProvider = velocityProvider;
        this.particleProvider = particleProvider;
    }

    @Override
    public Topology<Particle> iterate(Topology<Particle> topology) {
        Topology.Builder<Particle> topologyBuilder = topology.newBuilder();
//        for (Entity particle : topology) {
        // Update pbest (This should be automatic?)
        // Update gbest (This should be done through a provider)
//        }

        for (Particle particle : topology) {
            Velocity velocity = velocityProvider.create(particle); // New velocity
            MutableSeq newPosition = particle.solution().toMutableSeq().plus(velocity); // Update position
            Particle updatedParticle = particleProvider
                .position(CandidateSolution.copyOf(newPosition.toArray()))
                .velocity(velocity)
                .get();
            topologyBuilder.add(updatedParticle);
        }
        return topologyBuilder.build();
    }
}