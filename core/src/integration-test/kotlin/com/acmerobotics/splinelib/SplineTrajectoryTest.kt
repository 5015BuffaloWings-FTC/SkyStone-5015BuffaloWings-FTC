package com.acmerobotics.splinelib

import com.acmerobotics.splinelib.path.LineSegment
import com.acmerobotics.splinelib.path.Path
import com.acmerobotics.splinelib.path.QuinticSplineSegment
import com.acmerobotics.splinelib.trajectory.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SplineTrajectoryTest {
    companion object {
        private val BASE_CONSTRAINTS = DriveConstraints(50.0, 25.0, Math.PI / 2, Math.PI / 2, 500.0)
        private val CONSTRAINTS = TankConstraints(BASE_CONSTRAINTS, 12.0)
    }

    @Test
    fun testLineSegment() {
        val line = LineSegment(
                Vector2d(0.0, 0.0),
                Vector2d(25.0, 25.0)
        )
        val trajectory = Trajectory(listOf(
                PathTrajectorySegment(listOf(Path(line)), listOf(BASE_CONSTRAINTS))
        ))

        GraphUtil.saveParametricCurve("lineSegment/curve", line)
        GraphUtil.saveTrajectory("lineSegment/trajectory", trajectory)
    }

    @Test
    fun testSimpleSpline() {
        val spline = QuinticSplineSegment(
            Waypoint(0.0, 0.0, 20.0, 20.0),
            Waypoint(30.0, 15.0, -30.0, 10.0)
        )
        val trajectory = Trajectory(listOf(
            PathTrajectorySegment(listOf(Path(spline)), listOf(BASE_CONSTRAINTS))
        ))

        GraphUtil.saveParametricCurve("simpleSpline/curve", spline)
        GraphUtil.saveTrajectory("simpleSpline/trajectory", trajectory)
    }

    @Test
    fun testCompositeSpline() {
        val line = LineSegment(
                Vector2d(0.0, 0.0),
                Vector2d(15.0, 15.0)
        )
        val spline = QuinticSplineSegment(
            Waypoint(15.0, 15.0, 15.0, 15.0),
            Waypoint(30.0, 15.0, 20.0, 5.0)
        )
        val trajectory = Trajectory(listOf(
            PathTrajectorySegment(listOf(Path(line), Path(spline)), listOf(CONSTRAINTS, CONSTRAINTS))
        ))

        GraphUtil.saveParametricCurve("compositeSpline/curve", spline)
        GraphUtil.saveTrajectory("compositeSpline/trajectory", trajectory)
    }
}