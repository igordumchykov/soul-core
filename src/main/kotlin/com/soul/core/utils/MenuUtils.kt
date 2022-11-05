package com.soul.core.utils

import com.soul.core.domain.BaseMenu
import com.soul.core.domain.Ingredient
import com.soul.core.domain.Item
import com.soul.core.domain.MenuGroup

class MenuUtils {
    companion object {
        fun attachImageUrl(src: BaseMenu, map: Map<String, String>) {
            src.imageUrl = map[src.imageUrl] ?: src.imageUrl
            src.groups?.forEach { attachImageUrl(it, map) }
        }

        fun attachImageUrl(src: MenuGroup, map: Map<String, String>) {
            src.imageUrl = map[src.imageUrl] ?: src.imageUrl
            src.groups?.forEach { attachImageUrl(it, map) }
            src.items?.forEach { attachImageUrl(it, map) }
        }

        private fun attachImageUrl(src: Item, map: Map<String, String>) {
            src.imageUrl = map[src.imageUrl] ?: src.imageUrl
            src.ingredients?.forEach { attachImageUrl(it, map) }
            src.extra?.forEach { attachImageUrl(it, map) }
        }

        private fun attachImageUrl(src: Ingredient, map: Map<String, String>) {
            src.imageUrl = map[src.imageUrl] ?: src.imageUrl
        }
    }
}