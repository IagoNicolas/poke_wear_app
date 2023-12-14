package com.example.poke_wear_app.presentation.api.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Pokemon(
    val abilities: List<Ability>,
    @JsonProperty("base_experience")
    val baseExperience: Long,
    val forms: List<Form>,
    @JsonProperty("game_indices")
    val gameIndices: List<Index>,
    val height: Long,
    @JsonProperty("held_items")
    val heldItems: List<HeldItem>,
    val id: Long,
    @JsonProperty("is_default")
    val isDefault: Boolean,
    @JsonProperty("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Mfe>,
    val name: String,
    val order: Long,
    @JsonProperty("past_abilities")
    val pastAbilities: List<Any?>,
    @JsonProperty("past_types")
    val pastTypes: List<Any?>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Long,
)

data class Ability(
    val ability: Ability2,
    @JsonProperty("is_hidden")
    val isHidden: Boolean,
    val slot: Long,
)

data class Ability2(
    val name: String,
    val url: String,
)

data class Form(
    val name: String,
    val url: String,
)

data class Index(
    @JsonProperty("game_index")
    val gameIndex: Long,
    val version: Version,
)

data class Version(
    val name: String,
    val url: String,
)

data class HeldItem(
    val item: Item,
    @JsonProperty("version_details")
    val versionDetails: List<VersionDetail>,
)

data class Item(
    val name: String,
    val url: String,
)

data class VersionDetail(
    val rarity: Long,
    val version: Version2,
)

data class Version2(
    val name: String,
    val url: String,
)

data class Mfe(
    val move: Move,
    @JsonProperty("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>,
)

data class Move(
    val name: String,
    val url: String,
)

data class VersionGroupDetail(
    @JsonProperty("level_learned_at")
    val levelLearnedAt: Long,
    @JsonProperty("move_learn_method")
    val moveLearnMethod: MoveLearnMethod,
    @JsonProperty("version_group")
    val versionGroup: VersionGroup,
)

data class MoveLearnMethod(
    val name: String,
    val url: String,
)

data class VersionGroup(
    val name: String,
    val url: String,
)

data class Species(
    val name: String,
    val url: String,
)

data class Sprites(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_female")
    val backFemale: Any?,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("back_shiny_female")
    val backShinyFemale: Any?,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
    val other: Other,
    val versions: Versions,
)

data class Other(
    @JsonProperty("dream_world")
    val dreamWorld: DreamWorld,
    val home: Home,
    @JsonProperty("official-artwork")
    val officialArtwork: OfficialArtwork,
)

data class DreamWorld(
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
)

data class Home(
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class OfficialArtwork(
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_shiny")
    val frontShiny: String,
)

data class Versions(
    @JsonProperty("generation-i")
    val generationI: GenerationI,
    @JsonProperty("generation-ii")
    val generationIi: GenerationIi,
    @JsonProperty("generation-iii")
    val generationIii: GenerationIii,
    @JsonProperty("generation-iv")
    val generationIv: GenerationIv,
    @JsonProperty("generation-v")
    val generationV: GenerationV,
    @JsonProperty("generation-vi")
    val generationVi: GenerationVi,
    @JsonProperty("generation-vii")
    val generationVii: GenerationVii,
    @JsonProperty("generation-viii")
    val generationViii: GenerationViii,
)

data class GenerationI(
    @JsonProperty("red-blue")
    val redBlue: RedBlue,
    val yellow: Yellow,
)

data class RedBlue(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_gray")
    val backGray: String,
    @JsonProperty("back_transparent")
    val backTransparent: String,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_gray")
    val frontGray: String,
    @JsonProperty("front_transparent")
    val frontTransparent: String,
)

data class Yellow(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_gray")
    val backGray: String,
    @JsonProperty("back_transparent")
    val backTransparent: String,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_gray")
    val frontGray: String,
    @JsonProperty("front_transparent")
    val frontTransparent: String,
)

data class GenerationIi(
    val crystal: Crystal,
    val gold: Gold,
    val silver: Silver,
)

data class Crystal(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("back_shiny_transparent")
    val backShinyTransparent: String,
    @JsonProperty("back_transparent")
    val backTransparent: String,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_transparent")
    val frontShinyTransparent: String,
    @JsonProperty("front_transparent")
    val frontTransparent: String,
)

data class Gold(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_transparent")
    val frontTransparent: String,
)

data class Silver(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_transparent")
    val frontTransparent: String,
)

data class GenerationIii(
    val emerald: Emerald,
    @JsonProperty("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen,
    @JsonProperty("ruby-sapphire")
    val rubySapphire: RubySapphire,
)

data class Emerald(
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_shiny")
    val frontShiny: String,
)

data class FireredLeafgreen(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_shiny")
    val frontShiny: String,
)

data class RubySapphire(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_shiny")
    val frontShiny: String,
)

data class GenerationIv(
    @JsonProperty("diamond-pearl")
    val diamondPearl: DiamondPearl,
    @JsonProperty("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver,
    val platinum: Platinum,
)

data class DiamondPearl(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_female")
    val backFemale: Any?,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("back_shiny_female")
    val backShinyFemale: Any?,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class HeartgoldSoulsilver(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_female")
    val backFemale: Any?,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("back_shiny_female")
    val backShinyFemale: Any?,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class Platinum(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_female")
    val backFemale: Any?,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("back_shiny_female")
    val backShinyFemale: Any?,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationV(
    @JsonProperty("black-white")
    val blackWhite: BlackWhite,
)

data class BlackWhite(
    val animated: Animated,
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_female")
    val backFemale: Any?,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("back_shiny_female")
    val backShinyFemale: Any?,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class Animated(
    @JsonProperty("back_default")
    val backDefault: String,
    @JsonProperty("back_female")
    val backFemale: Any?,
    @JsonProperty("back_shiny")
    val backShiny: String,
    @JsonProperty("back_shiny_female")
    val backShinyFemale: Any?,
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationVi(
    @JsonProperty("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire,
    @JsonProperty("x-y")
    val xY: XY,
)

data class OmegarubyAlphasapphire(
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class XY(
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationVii(
    val icons: Icons,
    @JsonProperty("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon,
)

data class Icons(
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
)

data class UltraSunUltraMoon(
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
    @JsonProperty("front_shiny")
    val frontShiny: String,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationViii(
    val icons: Icons2,
)

data class Icons2(
    @JsonProperty("front_default")
    val frontDefault: String,
    @JsonProperty("front_female")
    val frontFemale: Any?,
)

data class Stat(
    @JsonProperty("base_stat")
    val baseStat: Long,
    val effort: Long,
    val stat: Stat2,
)

data class Stat2(
    val name: String,
    val url: String,
)

data class Type(
    val slot: Long,
    val type: Type2,
)

data class Type2(
    val name: String,
    val url: String,
)
