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
package net.sourceforge.cilib.entity.initialization;

import net.sourceforge.cilib.entity.EntityType;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.math.random.generator.RandomProvider;
import net.sourceforge.cilib.pso.particle.StandardParticle;
import net.sourceforge.cilib.type.types.Blackboard;
import net.sourceforge.cilib.type.types.Type;
import net.sourceforge.cilib.type.types.container.StructuredType;
import net.sourceforge.cilib.type.types.container.Vector;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

/**
 *
 * @author gpampara
 */
@RunWith(JMock.class)
public class RandomInitializationStrategyTest {

    private Mockery mockery = new JUnit4Mockery();

    @Test
    public void testInitialize() {
        Vector expected = Vector.of(1.0, 1.0, 1.0);
        Particle particle = new StandardParticle();
        particle.getProperties().put(EntityType.CANDIDATE_SOLUTION, expected.getClone());

        RandomInitializationStrategy<Particle> strategy = new RandomInitializationStrategy<Particle>();
        strategy.initialize(EntityType.CANDIDATE_SOLUTION, particle);

        Vector position = (Vector) particle.getPosition();

        for (int i = 0; i < particle.getDimension(); i++) {
            Assert.assertThat(expected.doubleValueOf(i), is(not(equalTo(position.doubleValueOf(i)))));
        }
    }

    @Test
    public void randomized() {
        final Particle particle = mockery.mock(Particle.class);
        final StructuredType<?> randomizable = mockery.mock(StructuredType.class);
        final Blackboard<Enum<?>, Type> blackboard = new Blackboard<Enum<?>, Type>();
        blackboard.put(EntityType.CANDIDATE_SOLUTION, randomizable);

        RandomInitializationStrategy<Particle> strategy = new RandomInitializationStrategy<Particle>();

        mockery.checking(new Expectations() {{
            oneOf(particle).getProperties();
            will(returnValue(blackboard));
            oneOf(randomizable).randomize(with(any(RandomProvider.class)));
        }});

        strategy.initialize(EntityType.CANDIDATE_SOLUTION, particle);
    }
}
