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

import com.google.inject.util.Providers;
import net.cilib.collection.Topology;
import net.cilib.collection.immutable.ImmutableGBestTopology;
import net.cilib.entity.Entity;
import net.cilib.entity.Fitnesses;
import net.cilib.entity.Individual;
import org.junit.Assert;
import net.cilib.collection.immutable.ImmutableLBestTopology;
import net.cilib.entity.CandidateSolution;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author gpampara
 */
public class NeighborhoodBestTest {

    private Individual newMockIndividual(double fitness) {
        return new Individual(CandidateSolution.empty(), Fitnesses.newMaximizationFitness(fitness));
    }

    @Test
    public void lbestNeighborhood() {
        Individual target = newMockIndividual(3.0);
        ImmutableLBestTopology<Individual> topology = ImmutableLBestTopology.<Individual>newBuilder()
                .add(newMockIndividual(1.0))
                .add(newMockIndividual(2.0))
                .add(target)
                .add(newMockIndividual(4.0))
                .add(newMockIndividual(5.0))
                .build();
        NeighborhoodBest guide = new NeighborhoodBest(Providers.<Topology>of(topology));
        Entity result = guide.of(target);
        Assert.assertThat(result.fitness().value(), equalTo(4.0));
    }

    @Test
    public void gbestNeighborhood() {
        Individual target = newMockIndividual(3.0);
        ImmutableGBestTopology<Individual> topology = ImmutableGBestTopology.<Individual>newBuilder()
                .add(newMockIndividual(1.0))
                .add(newMockIndividual(2.0))
                .add(target)
                .add(newMockIndividual(4.0))
                .add(newMockIndividual(5.0))
                .build();
        NeighborhoodBest guide = new NeighborhoodBest(Providers.<Topology>of(topology));
        Entity result = guide.of(target);
        Assert.assertThat(result.fitness().value(), equalTo(5.0));
    }
}