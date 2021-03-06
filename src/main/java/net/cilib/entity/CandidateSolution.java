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

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import gnu.trove.TDoubleArrayList;
import java.util.Arrays;

/**
 * Immutable candidate solution. A candidate solution is the representation
 * of a solution within a given problem domain.
 *
 * @since 0.8
 * @author gpampara
 */
public final class CandidateSolution implements LinearSeq {

    private final static CandidateSolution EMPTY = new CandidateSolution(new TDoubleArrayList(new double[]{}));
    private final TDoubleArrayList internal;

    /**
     * Return an empty {@code CandidateSolution}. The same instance will be
     * returned on repetitive calls to this method.
     * @return the empty {@code CandidateSolution}.
     */
    public static CandidateSolution empty() {
        return EMPTY;
    }

    /**
     * Returns an immutable candidate solution, which is a copy of the given
     * argument.
     * @param solution the candidate solution to copy.
     * @return an immutable copy of a provided new candidate solution.
     */
    public static CandidateSolution copyOf(final CandidateSolution solution) {
        return new CandidateSolution(new TDoubleArrayList(solution.toArray()));
    }

    /**
     * Returns an immutable candidate solution containing the given elements,
     * in order.
     * @param solution the array of values, representing the candidate solution.
     * @return an immutable candidate solution representing the given values.
     */
    public static CandidateSolution of(final double... solution) {
        Preconditions.checkArgument(solution.length > 0);
        return new CandidateSolution(new TDoubleArrayList(solution));
    }

    /**
     * Create a {@code CandidateSolution} instance, filled up with the
     * provided {@code item} for {@code size} dimensions.
     * @param item used to fill the {@code CandidateSolution}
     * @param size the number of items to include within the
     *        {@code CandidateSolution}
     * @return A newly created {@code CandidateSolution} of filled
     *         {@code item}s.
     */
    public static CandidateSolution fill(final int item, final int size) {
        Builder builder = newBuilder();
        for (int i = 0; i < size; i++) {
            builder.add(item);
        }
        return builder.build();
    }

    private CandidateSolution(TDoubleArrayList list) {
        this.internal = list;
    }

    /**
     * Get the value of the candidate solution at the given {@code index}.
     * @param index position of the value
     * @return the value within the candidate solution at the given
     *  {@code index}.
     */
    @Override
    public double get(int index) {
        return internal.get(index);
    }

    /**
     * Returns the size of this {@code CandidateSolution}.
     * @return the candidate solution size.
     */
    @Override
    public int size() {
        return internal.size();
    }

    /**
     * Convert the {@code CandidateSolution} into a primitive array. The
     * returned array is copy of the contents of the {@code CandidateSolution}.
     *
     * @return a copy of the internal representation for this candidate
     * solution.
     */
    @Override
    public double[] toArray() {
        return internal.toNativeArray();
    }

    /**
     * Obtain a {@code String} representation of the {@code CandidateSolution}.
     * @return {@code String} representation.
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(internal).toString();
    }

    /**
     * Translate the {@code CandidateSolution} into a mutable instance. The
     * resulting mutable instance contains a copy of the
     * {@code CandidateSolution} representation and will not alter the
     * {@code CandidateSolution}.
     * @return a {@code MutableSeq}.
     */
    @Override
    public MutableSeq toMutableSeq() {
        return new MutableSeq(this);
    }

    /**
     * Create an iterator to traverse the contents of the
     * {@code CandidateSolution}. For the iterator, a defensive copy of the
     * current {@code CandidateSolution} representation is made.
     * @return {@code SeqIterator} instance for the iteration.
     */
    public SeqIterator iterator() {
        final double[] local = Arrays.copyOf(internal.toNativeArray(), internal.size());
        return new SeqIterator() {

            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < local.length;
            }

            @Override
            public double next() {
                return local[count++];
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CandidateSolution)) {
            return false;
        }

        CandidateSolution other = (CandidateSolution) obj;
        return internal.equals(other.internal);
    }

    @Override
    public int hashCode() {
        return internal.hashCode();
    }

    /**
     * Creates an instance of the internal {@linkplain Builder builder} for
     * creation of candidate solutions.
     * @return a new instance of the builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Builder to create {@link CandidateSolution} instances. After the builder
     * has created the {@link CandidateSolution}, the builder is reset to an
     * empty state.
     */
    public static class Builder implements Seq.Builder {

        private int current;
        private double[] internal; // Use TDoubleArrayList?

        private Builder() {
            current = 0;
            internal = new double[]{};
        }

        /**
         * Adds a {@code value} to the {@code CandidateSolution}.
         * @param value the value to add.
         * @return the current {@code Builder}.
         */
        @Override
        public Builder add(double value) {
            updateSize();
            internal[current] = value;
            current += 1;
            return this;
        }

        /**
         * Returns a newly created {@code CandidateSolution} based on the
         * contents of the {@code Builder}.
         * @return the newly created {@code CandidateSolution}.
         */
        @Override
        public CandidateSolution build() {
            try {
                double[] target = new double[current];
                System.arraycopy(internal, 0, target, 0, current);
                return CandidateSolution.of(target);
            } finally {
                current = 0;
                internal = new double[]{};
            }
        }

        private void updateSize() {
            if (current + 1 >= internal.length) {
                double[] tmp = new double[internal.length + 10];
                System.arraycopy(internal, 0, tmp, 0, internal.length);
                internal = tmp;
            }
        }

        /**
         * Create a copy of the provided {@code CandidateSolution} as the data
         * for the {@code Builder}. Note that this method is <b>destructive.</b>
         * Any data that is currently within the {@code Builder} will be
         * replaced by the data within the provided {@code CandidateSolution}.
         * @param candidateSolution sequence to seed the {@code Builder} with.
         * @return the {@code Builder} for method chaining.
         */
        public Builder copyOf(CandidateSolution candidateSolution) {
            final double[] solution = candidateSolution.toArray();
            internal = new double[solution.length];
            System.arraycopy(solution, 0, internal, 0, solution.length);
            return this;
        }
    }
}
