package com.varabyte.kobweb.icons.parsers.lucide

/** An SVG element, e.g. `<path d="M5 12h14"/>` as tag "path" with attributes {"d": "M5 12h14"}. */
data class SvgElement(val tag: String, val attributes: Map<String, String>)
