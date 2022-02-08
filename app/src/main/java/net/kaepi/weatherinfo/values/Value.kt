package net.kaepi.weatherinfo.values

import net.kaepi.weatherinfo.R

val TAB_TITLES = arrayOf(
	R.string.tab_text_1,
	R.string.tab_text_2,
	R.string.tab_text_3,
)

val AREA_LIST = arrayOf(
	AreaData("北海道", "016010"),
	AreaData("青森県", "020010"),
	AreaData("岩手県", "030020"),
	AreaData("宮城県", "040010"),
	AreaData("秋田県", "050010"),
	AreaData("山形県", "060010"),
	AreaData("福島県", "070010"),
	AreaData("茨城県", "080010"),
	AreaData("栃木県", "090010"),
	AreaData("群馬県", "100010"),
	AreaData("埼玉県", "110010"),
	AreaData("千葉県", "120010"),
	AreaData("東京都", "130010"),
	AreaData("神奈川県", "140010"),
	AreaData("新潟県", "150010"),
	AreaData("富山県", "160010"),
	AreaData("石川県", "170010"),
	AreaData("福井県", "180010"),
	AreaData("山梨県", "190010"),
	AreaData("長野県", "200010"),
	AreaData("岐阜県", "210010"),
	AreaData("静岡県", "220010"),
	AreaData("愛知県", "230010"),
	AreaData("三重県", "240010"),
	AreaData("滋賀県", "250010"),
	AreaData("京都府", "260010"),
	AreaData("大阪府", "270000"),
	AreaData("兵庫県", "280010"),
	AreaData("奈良県", "290010"),
	AreaData("和歌山県", "300010"),
	AreaData("鳥取県", "310010"),
	AreaData("島根県", "320010"),
	AreaData("岡山県", "330010"),
	AreaData("広島県", "340010"),
	AreaData("山口県", "350010"),
	AreaData("徳島県", "360010"),
	AreaData("香川県", "370000"),
	AreaData("愛媛県", "380010"),
	AreaData("高知県", "390010"),
	AreaData("福岡県", "400010"),
	AreaData("佐賀県", "410010"),
	AreaData("長崎県", "420010"),
	AreaData("熊本県", "430010"),
	AreaData("大分県", "440010"),
	AreaData("宮崎県", "450010"),
	AreaData("鹿児島県", "460010"),
	AreaData("沖縄県", "471010"),
)

data class AreaData(
	val name: String,
	val id: String
)
