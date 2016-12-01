/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016.utils

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class MovementSpec : Spek({
    describe("movement") {
        it("should not turn if the direction makes no sense") {
            assertThat(Movement.FacingNorth().turn('?') is Movement.FacingNorth).isTrue()
            assertThat(Movement.FacingSouth().turn('?') is Movement.FacingSouth).isTrue()
            assertThat(Movement.FacingEast().turn('?') is Movement.FacingEast).isTrue()
            assertThat(Movement.FacingWest().turn('?') is Movement.FacingWest).isTrue()
        }
        it("should turn left") {
            assertThat(Movement.FacingNorth().turn('L') is Movement.FacingWest).isTrue()
            assertThat(Movement.FacingSouth().turn('L') is Movement.FacingEast).isTrue()
            assertThat(Movement.FacingEast().turn('L') is Movement.FacingNorth).isTrue()
            assertThat(Movement.FacingWest().turn('L') is Movement.FacingSouth).isTrue()
        }
        it("should turn right") {
            assertThat(Movement.FacingNorth().turn('R') is Movement.FacingEast).isTrue()
            assertThat(Movement.FacingSouth().turn('R') is Movement.FacingWest).isTrue()
            assertThat(Movement.FacingEast().turn('R') is Movement.FacingSouth).isTrue()
            assertThat(Movement.FacingWest().turn('R') is Movement.FacingNorth).isTrue()
        }
        it("should move properly") {
            assertThat(Movement.FacingNorth().move(2).location()).isEqualTo(Pair(2, 0))
            assertThat(Movement.FacingSouth().move(2).location()).isEqualTo(Pair(-2, 0))
            assertThat(Movement.FacingEast().move(2).location()).isEqualTo(Pair(0, 2))
            assertThat(Movement.FacingWest().move(2).location()).isEqualTo(Pair(0, -2))
        }

    }
})