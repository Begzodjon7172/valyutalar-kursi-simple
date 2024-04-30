package uz.bnabiyev.valyutalarkursi.database

import uz.bnabiyev.valyutalarkursi.models.Valyuta

interface MyService {
    fun addValyuta(valyuta: Valyuta)

    fun listValyuta():ArrayList<Valyuta>

    fun getValyutaById(id:Int):Valyuta

    fun removeAllValyuta()

}