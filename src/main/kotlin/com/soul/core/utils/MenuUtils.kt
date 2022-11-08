package com.soul.core.utils

import com.soul.core.domain.BaseMenu
import com.soul.core.domain.Ingredient
import com.soul.core.domain.Item
import com.soul.core.domain.MenuGroup
import java.util.UUID

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

        fun generateSubIds(src: BaseMenu) {
            src.groups?.forEach { generateSubIds(it) }
        }

        private fun generateSubIds(src: MenuGroup) {
            src.subId = src.subId ?: UUID.randomUUID().toString()
            src.groups?.forEach { generateSubIds(it) }
            src.items?.forEach { generateSubIds(it) }
        }

        private fun generateSubIds(src: Item) {
            src.subId = src.subId ?: UUID.randomUUID().toString()
            src.ingredients?.forEach { generateSubId(it) }
            src.extra?.forEach { generateSubId(it) }
        }

        private fun generateSubId(src: Ingredient) {
            src.subId = src.subId ?: UUID.randomUUID().toString()
        }
    }
}