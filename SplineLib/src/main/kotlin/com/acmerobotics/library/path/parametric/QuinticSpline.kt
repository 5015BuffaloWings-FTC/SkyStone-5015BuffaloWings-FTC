package com.acmerobotics.library.path.parametric

import com.acmerobotics.library.Pose2d
import com.acmerobotics.library.Waypoint

object QuinticSpline {
    fun fromWaypoints(vararg waypoints: Waypoint) =
        CompositeCurve((0 until waypoints.lastIndex).map { QuinticSplineSegment(waypoints[it], waypoints[it+1]) })

    fun fromPoses(vararg poses: Pose2d): CompositeCurve {
        val poseDistances = (0 until poses.lastIndex)
            .map { poses[it+1].pos() distanceTo poses[it].pos() }
        val derivativeMagnitudes = (0 until poses.lastIndex - 1)
            .map { (poseDistances[it+1] + poseDistances[it]) / 2.0 }
            .toMutableList()
        derivativeMagnitudes.add(0, poseDistances.first())
        derivativeMagnitudes.add(poseDistances.last())
        return fromWaypoints(*(0 until poses.size).map { Waypoint(poses[it].x, poses[it].y,
            derivativeMagnitudes[it] * Math.cos(poses[it].heading),
                derivativeMagnitudes[it] * Math.sin(poses[it].heading)) }.toTypedArray())
    }
}