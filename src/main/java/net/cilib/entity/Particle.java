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
package net.cilib.entity;

import com.google.inject.Inject;
import java.util.Comparator;

/**
 * Representation of a {@code Particle}. A {@code Particle} is an {@code Entity}
 * that maintains a {@linkplain CandidateSolution current position},
 * {@linkplain Velocity velocity}, {@linkplain Fitness fitness} and also has a
 * memory to record the best observed {@linkplain CandidateSolution position}.
 * @author gpampara
 */
public final class Particle implements Entity, HasVelocity, HasMemory {

    private final CandidateSolution position;
    private final CandidateSolution bestPosition;
    private final Velocity velocity;
    private final Fitness fitness;

    /**
     * Create a new {@code Particle}.
     * @param position the current position.
     * @param bestPosition the best position observed.
     * @param velocity the current velocity.
     * @param fitness the fitness of the current position.
     */
    @Inject
    public Particle(CandidateSolution position,
            CandidateSolution bestPosition,
            Velocity velocity,
            Fitness fitness) {
        this.position = position;
        this.bestPosition = bestPosition;
        this.velocity = velocity;
        this.fitness = fitness;
    }

    /**
     * Gets the current position for the {@code Particle}.
     * {@inheritDoc}
     */
    @Override
    public CandidateSolution solution() {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return position.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fitness fitness() {
        return fitness;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity moreFit(Entity that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity moreFit(Entity that, Comparator<? super Entity> comparator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoreFit(Entity than) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity lessFit(Entity that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity lessFit(Entity that, Comparator<? super Entity> comparator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLessFit(Entity than) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equiv(Entity that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(Entity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Velocity velocity() {
        return velocity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CandidateSolution memory() {
        return bestPosition;
    }
}
