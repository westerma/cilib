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
package net.sourceforge.cilib.measurement.single.dynamic;

import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.measurement.Measurement;
import net.sourceforge.cilib.problem.FunctionOptimisationProblem;
import net.sourceforge.cilib.problem.MinimisationFitness;
import net.sourceforge.cilib.problem.OptimisationSolution;
import net.sourceforge.cilib.type.types.Real;
import net.sourceforge.cilib.type.types.container.Vector;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Julien Duhain
 */
@RunWith(JMock.class)
public class ErrorMeasurementTest {
    private Mockery mockery = new JUnit4Mockery()
    {{
       setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Test
    public void results() {
        final Algorithm algorithm = mockery.mock(Algorithm.class);
        final OptimisationSolution mockSolution = new OptimisationSolution(Vector.of(1.0), new MinimisationFitness(100.0));
        final FunctionOptimisationProblem mockProblem = mockery.mock(FunctionOptimisationProblem.class);

        mockery.checking(new Expectations() {{
            exactly(1).of(algorithm).getBestSolution(); will(returnValue(mockSolution));
            exactly(1).of(algorithm).getOptimisationProblem();will(returnValue(mockProblem));
            exactly(1).of(mockProblem).getError(Vector.of(1.0));will(returnValue(10.0));
        }});

        Measurement m = new ErrorMeasurement();
        Assert.assertEquals(10.0, ((Real) m.getValue(algorithm)).doubleValue(), 0.00001);
    }

}
