package com.soul.core.domain

import com.soul.core.config.TableNames
import org.springframework.data.mongodb.core.mapping.Document

@Document(TableNames.DRINK)
class Drinks(
    id: String? = null,
    name: LangString? = LangString(),
    description: LangString? = LangString(),
    imageUrl: String?,
    groups: ArrayList<MenuGroup>?
) : BaseMenu(id, name, description, imageUrl, groups)