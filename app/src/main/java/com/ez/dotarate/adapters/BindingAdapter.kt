package com.ez.dotarate.adapters

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ez.dotarate.App
import com.ez.dotarate.R
import com.ez.dotarate.constants.*
import com.squareup.picasso.Picasso
import java.util.*

object BindingAdapter {

    @BindingAdapter("url", "errorImage")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?, errorImage: Drawable) {
        if (TextUtils.isEmpty(url))
            view.setImageResource(R.drawable.ic_empty_avatar)
        else
            Picasso.get().load(url).placeholder(errorImage).error(
                errorImage
            ).into(view)
    }

    /**
     * Return ID Hero Image
     */
    @BindingAdapter("android:src")
    @JvmStatic
    fun findHeroImage(view: ImageView, heroID: Int) {

        when (heroID) {
            ANTI_MAGE -> view.setImageResource(R.drawable.anti_mage)
            AXE -> view.setImageResource(R.drawable.axe)
            BANE -> view.setImageResource(R.drawable.bane)
            BLOODSEEKER -> view.setImageResource(R.drawable.bloodseeker)
            CRYSTAL_MAIDEN -> view.setImageResource(R.drawable.crystal_maiden)
            DROW_RANGER -> view.setImageResource(R.drawable.drow_ranger)
            EARTHSHAKER -> view.setImageResource(R.drawable.earthshaker)
            JUGGERNAUT -> view.setImageResource(R.drawable.juggernaut)
            MORPHLING -> view.setImageResource(R.drawable.morphling)
            MIRANA -> view.setImageResource(R.drawable.mirana)
            SHADOW_FIEND -> view.setImageResource(R.drawable.sf)
            PHANTOM_LANCER -> view.setImageResource(R.drawable.phantom_lancer)
            PUCK -> view.setImageResource(R.drawable.puck)
            PUDGE -> view.setImageResource(R.drawable.pudge)
            RAZOR -> view.setImageResource(R.drawable.razor)
            SAND_KING -> view.setImageResource(R.drawable.sand_king)
            STORM -> view.setImageResource(R.drawable.storm)
            SVEN -> view.setImageResource(R.drawable.sven)
            TINY -> view.setImageResource(R.drawable.tiny)
            VENGEFUL_SPIRIT -> view.setImageResource(R.drawable.vengeful_spirit)
            WINDRANGER -> view.setImageResource(R.drawable.wr)
            ZEUS -> view.setImageResource(R.drawable.zeus)
            KUNKKA -> view.setImageResource(R.drawable.kunkka)
            LINA -> view.setImageResource(R.drawable.lina)
            LION -> view.setImageResource(R.drawable.lion)
            SHADOW_SHAMAN -> view.setImageResource(R.drawable.shadow_shaman)
            SLARDAR -> view.setImageResource(R.drawable.slardar)
            TIDEHUNTER -> view.setImageResource(R.drawable.tide)
            WITCH_DOCTOR -> view.setImageResource(R.drawable.wd)
            LICH -> view.setImageResource(R.drawable.lich)
            RIKI -> view.setImageResource(R.drawable.riki)
            ENIGMA -> view.setImageResource(R.drawable.enigma)
            TINKER -> view.setImageResource(R.drawable.tinker)
            SNIPER -> view.setImageResource(R.drawable.sniper)
            NECROPHOS -> view.setImageResource(R.drawable.necr)
            WARLOCK -> view.setImageResource(R.drawable.warlock)
            BEASTMASTER -> view.setImageResource(R.drawable.beastmaster)
            QUEEN_OF_PAIN -> view.setImageResource(R.drawable.qop)
            VENOMANCER -> view.setImageResource(R.drawable.venomancer)
            VOID -> view.setImageResource(R.drawable.faceless_void)
            WRAITH_KING -> view.setImageResource(R.drawable.wraith_king)
            DEATH_PROPHET -> view.setImageResource(R.drawable.death_prophet)
            PHANTOM_ASSASSIN -> view.setImageResource(R.drawable.phantom_assassin)
            PUGNA -> view.setImageResource(R.drawable.pugna)
            TEMPLAR_ASSASSIN -> view.setImageResource(R.drawable.templar_assassin)
            VIPER -> view.setImageResource(R.drawable.viper)
            LUNA -> view.setImageResource(R.drawable.luna)
            DRAGON_KNIGHT -> view.setImageResource(R.drawable.dragon_knight)
            DAZZLE -> view.setImageResource(R.drawable.dazzle)
            CLOCKWERK -> view.setImageResource(R.drawable.clockwerk)
            LESHRAC -> view.setImageResource(R.drawable.leshrac)
            FURION -> view.setImageResource(R.drawable.furion)
            LIFESTEALER -> view.setImageResource(R.drawable.lifestealer)
            DARK_SEER -> view.setImageResource(R.drawable.dark_seer)
            CLINKZ -> view.setImageResource(R.drawable.clinkz)
            OMNI -> view.setImageResource(R.drawable.omni)
            ENCHANTRESS -> view.setImageResource(R.drawable.enchantress)
            HUSKAR -> view.setImageResource(R.drawable.huskar)
            NIGHT_STALKER -> view.setImageResource(R.drawable.night_stalker)
            BROODMOTHER -> view.setImageResource(R.drawable.broodmother)
            BOUNTY_HUNTER -> view.setImageResource(R.drawable.bounty_hunter)
            WEAVER -> view.setImageResource(R.drawable.weaver)
            JAKIRO -> view.setImageResource(R.drawable.jakiro)
            BATRIDER -> view.setImageResource(R.drawable.batrider)
            CHEN -> view.setImageResource(R.drawable.chen)
            SPECTRE -> view.setImageResource(R.drawable.spectre)
            APPARATION -> view.setImageResource(R.drawable.apparation)
            DOOM -> view.setImageResource(R.drawable.doom)
            URSA -> view.setImageResource(R.drawable.ursa)
            SPIRIT_BREAKER -> view.setImageResource(R.drawable.spirit_breaker)
            GYROCOPTER -> view.setImageResource(R.drawable.gyrocopter)
            ALCHEMIST -> view.setImageResource(R.drawable.alchemist)
            INVOKER -> view.setImageResource(R.drawable.invoker)
            SILENCER -> view.setImageResource(R.drawable.silencer)
            OUTWORLD_DEVOURER -> view.setImageResource(R.drawable.outworld_devourer)
            LYCAN -> view.setImageResource(R.drawable.lycan)
            BREWMASTER -> view.setImageResource(R.drawable.brewmaster)
            SHADOW_DEMON -> view.setImageResource(R.drawable.shadow_demon)
            LONE_DRUID -> view.setImageResource(R.drawable.lone_druid)
            CHAOS_KNIGHT -> view.setImageResource(R.drawable.chaos_knight)
            MEEPO -> view.setImageResource(R.drawable.meepo)
            TREANT -> view.setImageResource(R.drawable.treant)
            OGRE -> view.setImageResource(R.drawable.ogre)
            UNDYING -> view.setImageResource(R.drawable.undying)
            RUBICK -> view.setImageResource(R.drawable.rubick)
            DISRUPTOR -> view.setImageResource(R.drawable.disruptor)
            NYX -> view.setImageResource(R.drawable.nyx)
            NAGA_SIREN -> view.setImageResource(R.drawable.naga_siren)
            KOTL -> view.setImageResource(R.drawable.kotl)
            IO -> view.setImageResource(R.drawable.io)
            VISAGE -> view.setImageResource(R.drawable.visage)
            SLARK -> view.setImageResource(R.drawable.slark)
            MEDUSA -> view.setImageResource(R.drawable.medusa)
            TROLL -> view.setImageResource(R.drawable.troll)
            CENTAUR -> view.setImageResource(R.drawable.centaur)
            MAGNUS -> view.setImageResource(R.drawable.magnus)
            TIMBERSAW -> view.setImageResource(R.drawable.timber)
            BRISTLEBACK -> view.setImageResource(R.drawable.bristleback)
            TUSKAR -> view.setImageResource(R.drawable.tusk)
            SKY -> view.setImageResource(R.drawable.sky)
            ABADDON -> view.setImageResource(R.drawable.abbadon)
            ELDER_TITAN -> view.setImageResource(R.drawable.elder_titan)
            LEGOIN_COMANDER -> view.setImageResource(R.drawable.legion_commander)
            TECHIES -> view.setImageResource(R.drawable.techies)
            EMBER_SPIRIT -> view.setImageResource(R.drawable.ember_spirit)
            EARTH_SPIRIT -> view.setImageResource(R.drawable.earth_spirit)
            UNDERLORD -> view.setImageResource(R.drawable.underlord)
            TERRORBLADE -> view.setImageResource(R.drawable.terrorblade)
            PHOENIX -> view.setImageResource(R.drawable.phoenix)
            ORACLE -> view.setImageResource(R.drawable.oracle)
            WYVERN -> view.setImageResource(R.drawable.wyvern)
            ARC_WARDEN -> view.setImageResource(R.drawable.arc_warden)
            MONKEY_KING -> view.setImageResource(R.drawable.monkey_king)
            DARK_WILLOW -> view.setImageResource(R.drawable.dark_willow)
            PANGOLIER -> view.setImageResource(R.drawable.pango)
            GRIMSTROKE -> view.setImageResource(R.drawable.grimstroke)
            MARS -> view.setImageResource(R.drawable.mars)
        }
    }

    /**
     * Return Hero Name
     */
    @BindingAdapter("heroName")
    @JvmStatic
    fun findHeroName(view: TextView, heroID: Int) {

        val context = App.applicationContext()

        val heroName: String

        when (heroID) {
            ANTI_MAGE -> heroName = context.getString(R.string.anti_mage)
            AXE -> heroName = context.getString(R.string.axe)
            BANE -> heroName = context.getString(R.string.bane)
            BLOODSEEKER -> heroName = context.getString(R.string.bloodseeker)
            CRYSTAL_MAIDEN -> heroName = context.getString(R.string.crystal_maiden)
            DROW_RANGER -> heroName = context.getString(R.string.drow_ranger)
            EARTHSHAKER -> heroName = context.getString(R.string.earthshaker)
            JUGGERNAUT -> heroName = context.getString(R.string.juggernaut)
            MORPHLING -> heroName = context.getString(R.string.morphling)
            MIRANA -> heroName = context.getString(R.string.mirana)
            SHADOW_FIEND -> heroName = context.getString(R.string.shadow_fiend)
            PHANTOM_LANCER -> heroName = context.getString(R.string.phantom_lancer)
            PUCK -> heroName = context.getString(R.string.puck)
            PUDGE -> heroName = context.getString(R.string.pudge)
            RAZOR -> heroName = context.getString(R.string.razor)
            SAND_KING -> heroName = context.getString(R.string.sand_king)
            STORM -> heroName = context.getString(R.string.storm)
            SVEN -> heroName = context.getString(R.string.sven)
            TINY -> heroName = context.getString(R.string.tiny)
            VENGEFUL_SPIRIT -> heroName = context.getString(R.string.vengeful_spirit)
            WINDRANGER -> heroName = context.getString(R.string.windranger)
            ZEUS -> heroName = context.getString(R.string.zeus)
            KUNKKA -> heroName = context.getString(R.string.kunkka)
            LINA -> heroName = context.getString(R.string.lina)
            LION -> heroName = context.getString(R.string.lion)
            SHADOW_SHAMAN -> heroName = context.getString(R.string.shadow_shaman)
            SLARDAR -> heroName = context.getString(R.string.slardar)
            TIDEHUNTER -> heroName = context.getString(R.string.tidehunter)
            WITCH_DOCTOR -> heroName = context.getString(R.string.witch_doctor)
            LICH -> heroName = context.getString(R.string.lich)
            RIKI -> heroName = context.getString(R.string.riki)
            ENIGMA -> heroName = context.getString(R.string.enigma)
            TINKER -> heroName = context.getString(R.string.tinker)
            SNIPER -> heroName = context.getString(R.string.sniper)
            NECROPHOS -> heroName = context.getString(R.string.necrophos)
            WARLOCK -> heroName = context.getString(R.string.warlock)
            BEASTMASTER -> heroName = context.getString(R.string.beastmaster)
            QUEEN_OF_PAIN -> heroName = context.getString(R.string.queen_of_pain)
            VENOMANCER -> heroName = context.getString(R.string.venomancer)
            VOID -> heroName = context.getString(R.string.faceless_void)
            WRAITH_KING -> heroName = context.getString(R.string.wraith_king)
            DEATH_PROPHET -> heroName = context.getString(R.string.death_prophet)
            PHANTOM_ASSASSIN -> heroName = context.getString(R.string.phantom_assassin)
            PUGNA -> heroName = context.getString(R.string.pugna)
            TEMPLAR_ASSASSIN -> heroName = context.getString(R.string.templar_assassin)
            VIPER -> heroName = context.getString(R.string.viper)
            LUNA -> heroName = context.getString(R.string.luna)
            DRAGON_KNIGHT -> heroName = context.getString(R.string.dragon_knight)
            DAZZLE -> heroName = context.getString(R.string.dazzle)
            CLOCKWERK -> heroName = context.getString(R.string.clockwerk)
            LESHRAC -> heroName = context.getString(R.string.leshrac)
            FURION -> heroName = context.getString(R.string.natures_prophet)
            LIFESTEALER -> heroName = context.getString(R.string.lifestealer)
            DARK_SEER -> heroName = context.getString(R.string.dark_seer)
            CLINKZ -> heroName = context.getString(R.string.clinkz)
            OMNI -> heroName = context.getString(R.string.omniknight)
            ENCHANTRESS -> heroName = context.getString(R.string.enchantress)
            HUSKAR -> heroName = context.getString(R.string.huskar)
            NIGHT_STALKER -> heroName = context.getString(R.string.night_stalker)
            BROODMOTHER -> heroName = context.getString(R.string.broodmother)
            BOUNTY_HUNTER -> heroName = context.getString(R.string.bounty_hunter)
            WEAVER -> heroName = context.getString(R.string.weaver)
            JAKIRO -> heroName = context.getString(R.string.jakiro)
            BATRIDER -> heroName = context.getString(R.string.batrider)
            CHEN -> heroName = context.getString(R.string.chen)
            SPECTRE -> heroName = context.getString(R.string.spectre)
            APPARATION -> heroName = context.getString(R.string.ancient_apparition)
            DOOM -> heroName = context.getString(R.string.doom)
            URSA -> heroName = context.getString(R.string.ursa)
            SPIRIT_BREAKER -> heroName = context.getString(R.string.spirit_breaker)
            GYROCOPTER -> heroName = context.getString(R.string.gyrocopter)
            ALCHEMIST -> heroName = context.getString(R.string.alchemist)
            INVOKER -> heroName = context.getString(R.string.invoker)
            SILENCER -> heroName = context.getString(R.string.silencer)
            OUTWORLD_DEVOURER -> heroName = context.getString(R.string.outworld_devourer)
            LYCAN -> heroName = context.getString(R.string.lycan)
            BREWMASTER -> heroName = context.getString(R.string.brewmaster)
            SHADOW_DEMON -> heroName = context.getString(R.string.shadow_demon)
            LONE_DRUID -> heroName = context.getString(R.string.lone_druid)
            CHAOS_KNIGHT -> heroName = context.getString(R.string.chaos_knight)
            MEEPO -> heroName = context.getString(R.string.meepo)
            TREANT -> heroName = context.getString(R.string.treant)
            OGRE -> heroName = context.getString(R.string.ogre_magi)
            UNDYING -> heroName = context.getString(R.string.undying)
            RUBICK -> heroName = context.getString(R.string.rubick)
            DISRUPTOR -> heroName = context.getString(R.string.disruptor)
            NYX -> heroName = context.getString(R.string.nyx_assassin)
            NAGA_SIREN -> heroName = context.getString(R.string.naga_siren)
            KOTL -> heroName = context.getString(R.string.keeper_of_the_light)
            IO -> heroName = context.getString(R.string.io)
            VISAGE -> heroName = context.getString(R.string.visage)
            SLARK -> heroName = context.getString(R.string.slark)
            MEDUSA -> heroName = context.getString(R.string.medusa)
            TROLL -> heroName = context.getString(R.string.troll_warlord)
            CENTAUR -> heroName = context.getString(R.string.centaur_warrunner)
            MAGNUS -> heroName = context.getString(R.string.magnus)
            TIMBERSAW -> heroName = context.getString(R.string.timbersaw)
            BRISTLEBACK -> heroName = context.getString(R.string.bristleback)
            TUSKAR -> heroName = context.getString(R.string.tuskar)
            SKY -> heroName = context.getString(R.string.skywrath_mage)
            ABADDON -> heroName = context.getString(R.string.abaddon)
            ELDER_TITAN -> heroName = context.getString(R.string.elder_titan)
            LEGOIN_COMANDER -> heroName = context.getString(R.string.legion_commander)
            TECHIES -> heroName = context.getString(R.string.techies)
            EMBER_SPIRIT -> heroName = context.getString(R.string.ember_spirit)
            EARTH_SPIRIT -> heroName = context.getString(R.string.earth_spirit)
            UNDERLORD -> heroName = context.getString(R.string.underlord)
            TERRORBLADE -> heroName = context.getString(R.string.terrorblade)
            PHOENIX -> heroName = context.getString(R.string.phoenix)
            ORACLE -> heroName = context.getString(R.string.oracle)
            WYVERN -> heroName = context.getString(R.string.winter_wyvern)
            ARC_WARDEN -> heroName = context.getString(R.string.arc_warden)
            MONKEY_KING -> heroName = context.getString(R.string.monkey_king)
            DARK_WILLOW -> heroName = context.getString(R.string.dark_willow)
            PANGOLIER -> heroName = context.getString(R.string.pangolier)
            GRIMSTROKE -> heroName = context.getString(R.string.grimstroke)
            MARS -> heroName = context.getString(R.string.mars)
            else -> heroName = context.getString(R.string.unknown_hero)
        }

        if (heroName.length > 15) view.textSize = 14F
        else view.textSize = 16F

        view.text = heroName
    }

    /**
     * Return Game Date String
     */
    @JvmStatic
    fun getTimeForGame(startTime: Long): String {

        val context = App.applicationContext()
        val result: String

        // Текущая дата
        val currentDate = Date()
        // Количество секунд прошедших с 1 января 1970г
        val currentTime = currentDate.time / 1000
        // Разница в секундах между текущей датой и датой игры
        val differenceTime = currentTime - startTime
        // Количество часов прошедших после игры
        val hours = differenceTime.toInt() / 3600
        // Количество дней прошедших после игры
        val days = differenceTime.toInt() / 3600 / 24
        // Количество месяцев
        val months = differenceTime.toInt() / 3600 / 24 / 30
        // Количество минут прошедших после игры
        var minutes = 0

        when {
            hours < 1 -> {
                minutes = differenceTime.toInt() / 60
                result = if (minutes == 1) context.getString(R.string.minute)
                else String.format(context.getString(R.string.minutes), minutes)
            }
            hours == 1 -> result = context.getString(R.string.hour)
            hours < 24 -> result = String.format(context.getString(R.string.hours), hours)
            days == 1 -> result = context.getString(R.string.day)
            days < 31 -> result = String.format(context.getString(R.string.days), days)
            months == 1 -> result = context.getString(R.string.month)
            months < 12 -> result = String.format(context.getString(R.string.months), months)
            else -> result = context.getString(R.string.year)
        }

        return result
    }

    /**
     * Return Game result.
     * Win or Lose
     */
    @BindingAdapter("playerSlot", "leaverStatus", "radiantWin")
    @JvmStatic
    fun winOrLose(view: TextView, playerSlot: Int, leaverStatus: Int, radiantWin: Boolean) {
        val context = App.applicationContext()

        if (!(ABANDONED == leaverStatus && leaverStatus != ABANDONED_TWO)) {
            view.text = context.getString(R.string.abandoned)
            view.textSize = 14F
            view.setTextColor(context.getColor(R.color.colorRed))
        }

        if (playerSlot in 0..4) {
            if (radiantWin) {
                view.text = context.getString(R.string.won_game)
                view.textSize = 16F
                view.setTextColor(context.getColor(R.color.colorLightGreen))
            } else {
                view.text = context.getString(R.string.lost_game)
                view.textSize = 16F
                view.setTextColor(context.getColor(R.color.colorRed))
            }
        } else if (playerSlot in 128..132) {
            if (!radiantWin) {
                view.text = context.getString(R.string.won_game)
                view.textSize = 16F
                view.setTextColor(context.getColor(R.color.colorLightGreen))
            } else {
                view.text = context.getString(R.string.lost_game)
                view.textSize = 16F
                view.setTextColor(context.getColor(R.color.colorRed))
            }
        }
    }

    /**
     * Determines ranked or not ranked
     */
    @JvmStatic
    fun rankedOrNot(lobbyType: Int): String {
        val context = App.applicationContext()

        return if (lobbyType == RANKED) context.getString(R.string.ranked)
        else context.getString(R.string.unranked)
    }

    /**
     * Determines Game Mode
     */
    @JvmStatic
    fun gameMode(mode: Int): String {
        val context = App.applicationContext()

        return when (mode) {
            ALL_PICK, ALL_DRAFT -> context.getString(R.string.all_pick)
            SINGLE_DRAFT -> context.getString(R.string.single_draft)
            CAPTAINS_MODE -> context.getString(R.string.captains_mode)
            ALL_RANDOM -> context.getString(R.string.all_random)
            RANDOM_DRAFT -> context.getString(R.string.random_draft)
            else -> context.getString(R.string.unknown_mode)
        }
    }

    /**
     * Determines Skill Bracket
     */
    @JvmStatic
    fun skillPlayer(skill: Int): String {
        val context = App.applicationContext()

        return when (skill) {
            NORMALL_SKILL -> context.getString(R.string.normal_skill)
            HIGH_SKILL -> context.getString(R.string.high_skill)
            VERY_HIGH_SKILL -> context.getString(R.string.very_high_skill)
            else -> context.getString(R.string.unknown_skill)
        }
    }
}