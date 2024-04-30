package uz.bnabiyev.valyutalarkursi.models

class Valyuta {
    var id: Int = 0
    var Ccy: String? = null
    var CcyNm_EN: String? = null
    var CcyNm_RU: String? = null
    var CcyNm_UZ: String? = null
    var CcyNm_UZC: String? = null
    var Code: String? = null
    var Date: String? = null
    var Diff: String? = null
    var Nominal: String? = null
    var Rate: String? = null


    constructor(
        id: Int,
        Ccy: String?,
        CcyNm_EN: String?,
        CcyNm_RU: String?,
        CcyNm_UZ: String?,
        CcyNm_UZC: String?,
        Code: String?,
        Date: String?,
        Diff: String?,
        Nominal: String?,
        Rate: String?
    ) {
        this.id = id
        this.Ccy = Ccy
        this.CcyNm_EN = CcyNm_EN
        this.CcyNm_RU = CcyNm_RU
        this.CcyNm_UZ = CcyNm_UZ
        this.CcyNm_UZC = CcyNm_UZC
        this.Code = Code
        this.Date = Date
        this.Diff = Diff
        this.Nominal = Nominal
        this.Rate = Rate
    }

    constructor(
        Ccy: String?,
        CcyNm_EN: String?,
        CcyNm_RU: String?,
        CcyNm_UZ: String?,
        CcyNm_UZC: String?,
        Code: String?,
        Date: String?,
        Diff: String?,
        Nominal: String?,
        Rate: String?
    ) {
        this.Ccy = Ccy
        this.CcyNm_EN = CcyNm_EN
        this.CcyNm_RU = CcyNm_RU
        this.CcyNm_UZ = CcyNm_UZ
        this.CcyNm_UZC = CcyNm_UZC
        this.Code = Code
        this.Date = Date
        this.Diff = Diff
        this.Nominal = Nominal
        this.Rate = Rate
    }
}