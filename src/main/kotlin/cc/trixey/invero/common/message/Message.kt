package cc.trixey.invero.common.message

import taboolib.module.chat.ComponentText
import taboolib.module.chat.Components
import taboolib.module.chat.Source

/**
 * Message
 *
 * @author TheFloodDragon
 * @since 2025/3/8 14:33
 */
object Message {

    @JvmStatic
    fun parseAdventure(source: String): ComponentText {
        return Components.parseSimple(source)
            .buildColored()
            .unitalic()
    }

    /**
     * 将 [ComponentText] 转换成 Json字符串
     */
    @JvmStatic
    fun transformToJson(component: Source): String = component.toRawMessage()
}