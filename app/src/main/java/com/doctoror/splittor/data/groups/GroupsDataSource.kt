package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.groups.Group
import io.reactivex.rxjava3.core.Observable

interface GroupsDataSource {

    fun observe(): Observable<List<Group>>
}
