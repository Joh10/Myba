$wnd.com_vaadin_tapio_googlemaps_demo_DemoWidgetset.runAsyncCallback9("var $intern_1294 = 'com.vaadin.client.ui.colorpicker', $intern_1296 = 'showDefaultCaption', $intern_1297 = 'popupVisible', $intern_1298 = 'setColor', $intern_1299 = 'setOpen', $intern_1300 = 'background', $intern_1301 = 'v-colorpicker', $intern_1302 = {51:1, 7:1, 18:1, 29:1, 35:1, 33:1, 31:1, 32:1, 3:1}, $intern_1308 = 'v-default-caption-width';\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(1, null, {});\n_.getClass__Ljava_lang_Class_2$ = function java_lang_Object_getClass__Ljava_lang_Class_2(){\n  return this.java_lang_Object__1_1_1clazz$;\n}\n;\nfunction com_google_gwt_dom_client_Document_$createColElement__Lcom_google_gwt_dom_client_Document_2Lcom_google_gwt_dom_client_TableColElement_2(this$static){\n  return (com_google_gwt_dom_client_DOMImpl_$clinit__V() , com_google_gwt_dom_client_DOMImpl_impl).createElement__Lcom_google_gwt_dom_client_Document_2Ljava_lang_String_2Lcom_google_gwt_dom_client_Element_2(this$static, 'col');\n}\n\nfunction com_google_gwt_event_dom_client_MouseEvent_$getY__Lcom_google_gwt_event_dom_client_MouseEvent_2I(this$static){\n  var relativeElem;\n  relativeElem = this$static.com_google_gwt_event_dom_client_DomEvent_relativeElem;\n  if (relativeElem) {\n    return com_google_gwt_event_dom_client_MouseEvent_$getRelativeY__Lcom_google_gwt_event_dom_client_MouseEvent_2Lcom_google_gwt_dom_client_Element_2I(this$static, relativeElem);\n  }\n  return com_google_gwt_dom_client_NativeEvent_$getClientY__Lcom_google_gwt_dom_client_NativeEvent_2I(this$static.com_google_gwt_event_dom_client_DomEvent_nativeEvent);\n}\n\nfunction com_google_gwt_user_client_ui_AbsolutePanel_$add__Lcom_google_gwt_user_client_ui_AbsolutePanel_2Lcom_google_gwt_user_client_ui_Widget_2IIV(this$static, w, left, top_0){\n  var beforeIndex;\n  com_google_gwt_user_client_ui_Widget_$removeFromParent__Lcom_google_gwt_user_client_ui_Widget_2V(w);\n  beforeIndex = this$static.com_google_gwt_user_client_ui_ComplexPanel_children.com_google_gwt_user_client_ui_WidgetCollection_size;\n  this$static.setWidgetPositionImpl__Lcom_google_gwt_user_client_ui_Widget_2IIV(w, left, top_0);\n  com_google_gwt_user_client_ui_ComplexPanel_$insert__Lcom_google_gwt_user_client_ui_ComplexPanel_2Lcom_google_gwt_user_client_ui_Widget_2Lcom_google_gwt_user_client_Element_2IZV(this$static, w, (com_google_gwt_user_client_DOM_$clinit__V() , this$static.com_google_gwt_user_client_ui_UIObject_element), beforeIndex, true);\n}\n\nfunction com_google_gwt_user_client_ui_AbsolutePanel_$setWidgetPositionImpl__Lcom_google_gwt_user_client_ui_AbsolutePanel_2Lcom_google_gwt_user_client_ui_Widget_2IIV(w, left, top_0){\n  var h;\n  h = (com_google_gwt_user_client_DOM_$clinit__V() , w.com_google_gwt_user_client_ui_UIObject_element);\n  if (left == -1 && top_0 == -1) {\n    com_google_gwt_user_client_ui_AbsolutePanel_changeToStaticPositioning__Lcom_google_gwt_dom_client_Element_2V(h);\n  }\n   else {\n    com_google_gwt_dom_client_Style_$setPropertyImpl__Lcom_google_gwt_dom_client_Style_2Ljava_lang_String_2Ljava_lang_String_2V(h.style, $intern_83, $intern_85);\n    com_google_gwt_dom_client_Style_$setPropertyImpl__Lcom_google_gwt_dom_client_Style_2Ljava_lang_String_2Ljava_lang_String_2V(h.style, $intern_112, left + $intern_122);\n    com_google_gwt_dom_client_Style_$setPropertyImpl__Lcom_google_gwt_dom_client_Style_2Ljava_lang_String_2Ljava_lang_String_2V(h.style, $intern_275, top_0 + $intern_122);\n  }\n}\n\nfunction com_google_gwt_user_client_ui_AbsolutePanel_AbsolutePanel__V(){\n  com_google_gwt_user_client_ui_AbsolutePanel_AbsolutePanel__Lcom_google_gwt_dom_client_Element_2V.call(this, (com_google_gwt_user_client_DOM_$clinit__V() , com_google_gwt_dom_client_Document_$createDivElement__Lcom_google_gwt_dom_client_Document_2Lcom_google_gwt_dom_client_DivElement_2($doc)));\n  this.com_google_gwt_user_client_ui_UIObject_element.style[$intern_83] = $intern_444;\n  this.com_google_gwt_user_client_ui_UIObject_element.style[$intern_315] = $intern_94;\n}\n\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(485, 231, $intern_274, com_google_gwt_user_client_ui_AbsolutePanel_AbsolutePanel__V);\n_.setWidgetPositionImpl__Lcom_google_gwt_user_client_ui_Widget_2IIV = function com_google_gwt_user_client_ui_AbsolutePanel_setWidgetPositionImpl__Lcom_google_gwt_user_client_ui_Widget_2IIV(w, left, top_0){\n  com_google_gwt_user_client_ui_AbsolutePanel_$setWidgetPositionImpl__Lcom_google_gwt_user_client_ui_AbsolutePanel_2Lcom_google_gwt_user_client_ui_Widget_2IIV(w, left, top_0);\n}\n;\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(130, 9, $intern_278);\n_.addClickHandler__Lcom_google_gwt_event_dom_client_ClickHandler_2Lcom_google_gwt_event_shared_HandlerRegistration_2 = function com_google_gwt_user_client_ui_FocusWidget_addClickHandler__Lcom_google_gwt_event_dom_client_ClickHandler_2Lcom_google_gwt_event_shared_HandlerRegistration_2(handler){\n  return com_google_gwt_user_client_ui_Widget_$addDomHandler__Lcom_google_gwt_user_client_ui_Widget_2Lcom_google_gwt_event_shared_EventHandler_2Lcom_google_gwt_event_dom_client_DomEvent$Type_2Lcom_google_gwt_event_shared_HandlerRegistration_2(this, handler, (com_google_gwt_event_dom_client_ClickEvent_$clinit__V() , com_google_gwt_event_dom_client_ClickEvent_$clinit__V() , com_google_gwt_event_dom_client_ClickEvent_TYPE));\n}\n;\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(691, 26, $intern_289);\n_.addClickHandler__Lcom_google_gwt_event_dom_client_ClickHandler_2Lcom_google_gwt_event_shared_HandlerRegistration_2 = function com_google_gwt_user_client_ui_HTMLTable_addClickHandler__Lcom_google_gwt_event_dom_client_ClickHandler_2Lcom_google_gwt_event_shared_HandlerRegistration_2(handler){\n  return com_google_gwt_user_client_ui_Widget_$addDomHandler__Lcom_google_gwt_user_client_ui_Widget_2Lcom_google_gwt_event_shared_EventHandler_2Lcom_google_gwt_event_dom_client_DomEvent$Type_2Lcom_google_gwt_event_shared_HandlerRegistration_2(this, handler, (com_google_gwt_event_dom_client_ClickEvent_$clinit__V() , com_google_gwt_event_dom_client_ClickEvent_$clinit__V() , com_google_gwt_event_dom_client_ClickEvent_TYPE));\n}\n;\nfunction com_google_gwt_user_client_ui_Grid_$getCellCount__Lcom_google_gwt_user_client_ui_Grid_2II(this$static){\n  return this$static.com_google_gwt_user_client_ui_Grid_numColumns;\n}\n\nfunction com_google_gwt_user_client_ui_Grid_$prepareRow__Lcom_google_gwt_user_client_ui_Grid_2IV(this$static, row){\n  if (row < 0) {\n    throw new java_lang_IndexOutOfBoundsException_IndexOutOfBoundsException__Ljava_lang_String_2V('Cannot access a row with a negative index: ' + row);\n  }\n  if (row >= this$static.com_google_gwt_user_client_ui_Grid_numRows) {\n    throw new java_lang_IndexOutOfBoundsException_IndexOutOfBoundsException__Ljava_lang_String_2V($intern_287 + row + $intern_288 + this$static.com_google_gwt_user_client_ui_Grid_numRows);\n  }\n}\n\nfunction com_google_gwt_user_client_ui_Grid_$removeRow__Lcom_google_gwt_user_client_ui_Grid_2IV(this$static, row){\n  com_google_gwt_user_client_ui_HTMLTable_$removeRow__Lcom_google_gwt_user_client_ui_HTMLTable_2IV(this$static, row);\n  --this$static.com_google_gwt_user_client_ui_Grid_numRows;\n}\n\nfunction com_google_gwt_user_client_ui_Grid_$resizeColumns__Lcom_google_gwt_user_client_ui_Grid_2IV(this$static, columns){\n  var i, j, com_google_gwt_user_client_ui_HTMLTable_$removeCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_td_0, com_google_gwt_user_client_ui_HTMLTable_$removeCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_tr_0, com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_tr_0, com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_td_0, com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_com_google_gwt_user_client_ui_Grid_$createCell__Lcom_google_gwt_user_client_ui_Grid_2Lcom_google_gwt_user_client_Element_2_td_0_0;\n  if (this$static.com_google_gwt_user_client_ui_Grid_numColumns == columns) {\n    return;\n  }\n  if (columns < 0) {\n    throw new java_lang_IndexOutOfBoundsException_IndexOutOfBoundsException__Ljava_lang_String_2V('Cannot set number of columns to ' + columns);\n  }\n  if (this$static.com_google_gwt_user_client_ui_Grid_numColumns > columns) {\n    for (i = 0; i < this$static.com_google_gwt_user_client_ui_Grid_numRows; i++) {\n      for (j = this$static.com_google_gwt_user_client_ui_Grid_numColumns - 1; j >= columns; j--) {\n        com_google_gwt_user_client_ui_HTMLTable_$checkCellBounds__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV(this$static, i, j);\n        com_google_gwt_user_client_ui_HTMLTable_$removeCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_td_0 = com_google_gwt_user_client_ui_HTMLTable_$cleanCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIZLcom_google_gwt_dom_client_Element_2(this$static, i, j, false);\n        com_google_gwt_user_client_ui_HTMLTable_$removeCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_tr_0 = com_google_gwt_user_client_ui_HTMLTable$RowFormatter_$getRow__Lcom_google_gwt_user_client_ui_HTMLTable$RowFormatter_2Lcom_google_gwt_dom_client_Element_2ILcom_google_gwt_user_client_Element_2(this$static.com_google_gwt_user_client_ui_HTMLTable_bodyElem, i);\n        com_google_gwt_user_client_ui_HTMLTable_$removeCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_tr_0.removeChild(com_google_gwt_user_client_ui_HTMLTable_$removeCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_td_0);\n      }\n    }\n  }\n   else {\n    for (i = 0; i < this$static.com_google_gwt_user_client_ui_Grid_numRows; i++) {\n      for (j = this$static.com_google_gwt_user_client_ui_Grid_numColumns; j < columns; j++) {\n        com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_tr_0 = com_google_gwt_user_client_ui_HTMLTable$RowFormatter_$getRow__Lcom_google_gwt_user_client_ui_HTMLTable$RowFormatter_2Lcom_google_gwt_dom_client_Element_2ILcom_google_gwt_user_client_Element_2(this$static.com_google_gwt_user_client_ui_HTMLTable_bodyElem, i);\n        com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_td_0 = (com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_com_google_gwt_user_client_ui_Grid_$createCell__Lcom_google_gwt_user_client_ui_Grid_2Lcom_google_gwt_user_client_Element_2_td_0_0 = (com_google_gwt_user_client_DOM_$clinit__V() , com_google_gwt_dom_client_Document_$createTDElement__Lcom_google_gwt_dom_client_Document_2Lcom_google_gwt_dom_client_TableCellElement_2($doc)) , com_google_gwt_dom_client_Element_$setInnerHTML__Lcom_google_gwt_dom_client_Element_2Ljava_lang_String_2V(com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_com_google_gwt_user_client_ui_Grid_$createCell__Lcom_google_gwt_user_client_ui_Grid_2Lcom_google_gwt_user_client_Element_2_td_0_0, $intern_432) , com_google_gwt_user_client_DOM_$clinit__V() , com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_com_google_gwt_user_client_ui_Grid_$createCell__Lcom_google_gwt_user_client_ui_Grid_2Lcom_google_gwt_user_client_Element_2_td_0_0);\n        com_google_gwt_user_client_DOM_impl.insertChild__Lcom_google_gwt_dom_client_Element_2Lcom_google_gwt_dom_client_Element_2IV(com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_tr_0, com_google_gwt_user_client_DOM_resolve__Lcom_google_gwt_dom_client_Element_2Lcom_google_gwt_dom_client_Element_2(com_google_gwt_user_client_ui_HTMLTable_$insertCell__Lcom_google_gwt_user_client_ui_HTMLTable_2IIV_td_0), j);\n      }\n    }\n  }\n  this$static.com_google_gwt_user_client_ui_Grid_numColumns = columns;\n  com_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_$resizeColumnGroup__Lcom_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_2IZV(this$static.com_google_gwt_user_client_ui_HTMLTable_columnFormatter, columns, false);\n}\n\nfunction com_google_gwt_user_client_ui_Grid_$resizeRows__Lcom_google_gwt_user_client_ui_Grid_2IV(this$static, rows_0){\n  if (this$static.com_google_gwt_user_client_ui_Grid_numRows == rows_0) {\n    return;\n  }\n  if (rows_0 < 0) {\n    throw new java_lang_IndexOutOfBoundsException_IndexOutOfBoundsException__Ljava_lang_String_2V('Cannot set number of rows to ' + rows_0);\n  }\n  if (this$static.com_google_gwt_user_client_ui_Grid_numRows < rows_0) {\n    com_google_gwt_user_client_ui_Grid_addRows__Lcom_google_gwt_dom_client_Element_2IIV((com_google_gwt_user_client_DOM_$clinit__V() , this$static.com_google_gwt_user_client_ui_HTMLTable_bodyElem), rows_0 - this$static.com_google_gwt_user_client_ui_Grid_numRows, this$static.com_google_gwt_user_client_ui_Grid_numColumns);\n    this$static.com_google_gwt_user_client_ui_Grid_numRows = rows_0;\n  }\n   else {\n    while (this$static.com_google_gwt_user_client_ui_Grid_numRows > rows_0) {\n      com_google_gwt_user_client_ui_Grid_$removeRow__Lcom_google_gwt_user_client_ui_Grid_2IV(this$static, this$static.com_google_gwt_user_client_ui_Grid_numRows - 1);\n    }\n  }\n}\n\nfunction com_google_gwt_user_client_ui_Grid_Grid__IIV(rows_0, columns){\n  com_google_gwt_user_client_ui_HTMLTable_$clinit__V();\n  com_google_gwt_user_client_ui_HTMLTable_HTMLTable__V.call(this);\n  com_google_gwt_user_client_ui_HTMLTable_$setCellFormatter__Lcom_google_gwt_user_client_ui_HTMLTable_2Lcom_google_gwt_user_client_ui_HTMLTable$CellFormatter_2V(this, new com_google_gwt_user_client_ui_HTMLTable$CellFormatter_HTMLTable$CellFormatter__Lcom_google_gwt_user_client_ui_HTMLTable_2V(this));\n  com_google_gwt_user_client_ui_HTMLTable_$setRowFormatter__Lcom_google_gwt_user_client_ui_HTMLTable_2Lcom_google_gwt_user_client_ui_HTMLTable$RowFormatter_2V(this, new com_google_gwt_user_client_ui_HTMLTable$RowFormatter_HTMLTable$RowFormatter__Lcom_google_gwt_user_client_ui_HTMLTable_2V(this));\n  com_google_gwt_user_client_ui_HTMLTable_$setColumnFormatter__Lcom_google_gwt_user_client_ui_HTMLTable_2Lcom_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_2V(this, new com_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_HTMLTable$ColumnFormatter__Lcom_google_gwt_user_client_ui_HTMLTable_2V(this));\n  com_google_gwt_user_client_ui_Grid_$resizeColumns__Lcom_google_gwt_user_client_ui_Grid_2IV(this, columns);\n  com_google_gwt_user_client_ui_Grid_$resizeRows__Lcom_google_gwt_user_client_ui_Grid_2IV(this, rows_0);\n}\n\nfunction com_google_gwt_user_client_ui_Grid_addRows__Lcom_google_gwt_dom_client_Element_2IIV(table, rows_0, columns){\n  var td = $doc.createElement('td');\n  td.innerHTML = $intern_432;\n  var row = $doc.createElement('tr');\n  for (var cellNum = 0; cellNum < columns; cellNum++) {\n    var cell = td.cloneNode(true);\n    row.appendChild(cell);\n  }\n  table.appendChild(row);\n  for (var rowNum = 1; rowNum < rows_0; rowNum++) {\n    table.appendChild(row.cloneNode(true));\n  }\n}\n\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(531, 691, $intern_289, com_google_gwt_user_client_ui_Grid_Grid__IIV);\n_.getCellCount__II = function com_google_gwt_user_client_ui_Grid_getCellCount__II(row){\n  return com_google_gwt_user_client_ui_Grid_$getCellCount__Lcom_google_gwt_user_client_ui_Grid_2II(this);\n}\n;\n_.getRowCount__I = function com_google_gwt_user_client_ui_Grid_getRowCount__I(){\n  return this.com_google_gwt_user_client_ui_Grid_numRows;\n}\n;\n_.prepareCell__IIV = function com_google_gwt_user_client_ui_Grid_prepareCell__IIV(row, column){\n  com_google_gwt_user_client_ui_Grid_$prepareRow__Lcom_google_gwt_user_client_ui_Grid_2IV(this, row);\n  if (column < 0) {\n    throw new java_lang_IndexOutOfBoundsException_IndexOutOfBoundsException__Ljava_lang_String_2V('Cannot access a column with a negative index: ' + column);\n  }\n  if (column >= this.com_google_gwt_user_client_ui_Grid_numColumns) {\n    throw new java_lang_IndexOutOfBoundsException_IndexOutOfBoundsException__Ljava_lang_String_2V($intern_285 + column + $intern_286 + this.com_google_gwt_user_client_ui_Grid_numColumns);\n  }\n}\n;\n_.prepareRow__IV = function com_google_gwt_user_client_ui_Grid_prepareRow__IV(row){\n  com_google_gwt_user_client_ui_Grid_$prepareRow__Lcom_google_gwt_user_client_ui_Grid_2IV(this, row);\n}\n;\n_.com_google_gwt_user_client_ui_Grid_numColumns = 0;\n_.com_google_gwt_user_client_ui_Grid_numRows = 0;\nvar com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1user_1client_1ui_1Grid_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Class_2Ljava_lang_Class_2($intern_238, 'Grid', 531);\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(112, 482, $intern_292);\n_.addClickHandler__Lcom_google_gwt_event_dom_client_ClickHandler_2Lcom_google_gwt_event_shared_HandlerRegistration_2 = function com_google_gwt_user_client_ui_Label_addClickHandler__Lcom_google_gwt_event_dom_client_ClickHandler_2Lcom_google_gwt_event_shared_HandlerRegistration_2(handler){\n  return com_google_gwt_user_client_ui_Widget_$addDomHandler__Lcom_google_gwt_user_client_ui_Widget_2Lcom_google_gwt_event_shared_EventHandler_2Lcom_google_gwt_event_dom_client_DomEvent$Type_2Lcom_google_gwt_event_shared_HandlerRegistration_2(this, handler, (com_google_gwt_event_dom_client_ClickEvent_$clinit__V() , com_google_gwt_event_dom_client_ClickEvent_$clinit__V() , com_google_gwt_event_dom_client_ClickEvent_TYPE));\n}\n;\nfunction com_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_$resizeColumnGroup__Lcom_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_2IZV(this$static, columns, growOnly){\n  var i, num;\n  columns = columns > 1?columns:1;\n  num = this$static.com_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_columnGroup.childNodes.length;\n  if (num < columns) {\n    for (i = num; i < columns; i++) {\n      com_google_gwt_dom_client_Node_$appendChild__Lcom_google_gwt_dom_client_Node_2Lcom_google_gwt_dom_client_Node_2Lcom_google_gwt_dom_client_Node_2(this$static.com_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_columnGroup, com_google_gwt_dom_client_Document_$createColElement__Lcom_google_gwt_dom_client_Document_2Lcom_google_gwt_dom_client_TableColElement_2($doc));\n    }\n  }\n   else if (!growOnly && num > columns) {\n    for (i = num; i > columns; i--) {\n      com_google_gwt_dom_client_Node_$removeChild__Lcom_google_gwt_dom_client_Node_2Lcom_google_gwt_dom_client_Node_2Lcom_google_gwt_dom_client_Node_2(this$static.com_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_columnGroup, this$static.com_google_gwt_user_client_ui_HTMLTable$ColumnFormatter_columnGroup.lastChild);\n    }\n  }\n}\n\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(378, 9, $intern_299);\n_.addClickHandler__Lcom_google_gwt_event_dom_client_ClickHandler_2Lcom_google_gwt_event_shared_HandlerRegistration_2 = function com_google_gwt_user_client_ui_Image_addClickHandler__Lcom_google_gwt_event_dom_client_ClickHandler_2Lcom_google_gwt_event_shared_HandlerRegistration_2(handler){\n  return com_google_gwt_user_client_ui_Widget_$addHandler__Lcom_google_gwt_user_client_ui_Widget_2Lcom_google_gwt_event_shared_EventHandler_2Lcom_google_gwt_event_shared_GwtEvent$Type_2Lcom_google_gwt_event_shared_HandlerRegistration_2(this, handler, (com_google_gwt_event_dom_client_ClickEvent_$clinit__V() , com_google_gwt_event_dom_client_ClickEvent_$clinit__V() , com_google_gwt_event_dom_client_ClickEvent_TYPE));\n}\n;\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(850, 403, $intern_317);\n_.setWidgetPositionImpl__Lcom_google_gwt_user_client_ui_Widget_2IIV = function com_google_gwt_user_client_ui_RootPanel$DefaultRootPanel_setWidgetPositionImpl__Lcom_google_gwt_user_client_ui_Widget_2IIV(w, left, top_0){\n  left -= com_google_gwt_dom_client_Document_$getBodyOffsetLeft__Lcom_google_gwt_dom_client_Document_2I($doc);\n  top_0 -= com_google_gwt_dom_client_Document_$getBodyOffsetTop__Lcom_google_gwt_dom_client_Document_2I($doc);\n  com_google_gwt_user_client_ui_AbsolutePanel_$setWidgetPositionImpl__Lcom_google_gwt_user_client_ui_AbsolutePanel_2Lcom_google_gwt_user_client_ui_Widget_2IIV(w, left, top_0);\n}\n;\nfunction com_vaadin_client_metadata_ConnectorBundleLoaderImpl$1$1$250$1_$openPopup__Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$1$1$250$1_2ZV(this$static, p0){\n  com_vaadin_client_communication_RpcProxy$RpcInvokationHandler_$invoke__Lcom_vaadin_client_communication_RpcProxy$RpcInvokationHandler_2Ljava_lang_Object_2Lcom_vaadin_client_metadata_Method_2_3Ljava_lang_Object_2Ljava_lang_Object_2(this$static.com_vaadin_client_metadata_ConnectorBundleLoaderImpl$1$1$250$1_val$handler2, new com_vaadin_client_metadata_Method_Method__Lcom_vaadin_client_metadata_Type_2Ljava_lang_String_2V(new com_vaadin_client_metadata_Type_Type__Ljava_lang_Class_2V(com_google_gwt_lang_ClassLiteralHolder_Lcom_1vaadin_1shared_1ui_1colorpicker_1ColorPickerServerRpc_12_1classLit), 'openPopup'), com_google_gwt_lang_Array_initValues__Ljava_lang_Class_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2ILjava_lang_Object_2Ljava_lang_Object_2(com_google_gwt_lang_Array_getClassLiteralForArray__Ljava_lang_Class_2ILjava_lang_Class_2(com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1Object_12_1classLit, 1), $intern_10, 1, 3, [(java_lang_Boolean_$clinit__V() , p0?java_lang_Boolean_TRUE:java_lang_Boolean_FALSE)]));\n}\n\nfunction com_vaadin_client_ui_colorpicker_AbstractColorPickerConnector_$getCaption__Lcom_vaadin_client_ui_colorpicker_AbstractColorPickerConnector_2Ljava_lang_String_2(this$static){\n  if ((!this$static.com_vaadin_client_ui_AbstractConnector_state && (this$static.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this$static)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this$static.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_ui_colorpicker_ColorPickerState_showDefaultCaption && ((!this$static.com_vaadin_client_ui_AbstractConnector_state && (this$static.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this$static)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this$static.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_AbstractComponentState_caption == null || java_lang_String_$equals__Ljava_lang_String_2Ljava_lang_Object_2Z('', (!this$static.com_vaadin_client_ui_AbstractConnector_state && (this$static.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this$static)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this$static.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_AbstractComponentState_caption))) {\n    return (!this$static.com_vaadin_client_ui_AbstractConnector_state && (this$static.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this$static)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this$static.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_ui_colorpicker_ColorPickerState_color;\n  }\n  return (!this$static.com_vaadin_client_ui_AbstractConnector_state && (this$static.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this$static)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this$static.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_AbstractComponentState_caption;\n}\n\nfunction com_vaadin_client_ui_colorpicker_AbstractColorPickerConnector_AbstractColorPickerConnector__V(){\n  com_vaadin_client_ui_AbstractComponentConnector_AbstractComponentConnector__V.call(this);\n}\n\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(679, 33, $intern_1302);\n_.delegateCaptionHandling__Z = function com_vaadin_client_ui_colorpicker_AbstractColorPickerConnector_delegateCaptionHandling__Z(){\n  return false;\n}\n;\n_.getState__Lcom_vaadin_shared_AbstractComponentState_2 = function com_vaadin_client_ui_colorpicker_AbstractColorPickerConnector_getState__Lcom_vaadin_shared_AbstractComponentState_2(){\n  return !this.com_vaadin_client_ui_AbstractConnector_state && (this.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.com_vaadin_client_ui_AbstractConnector_state, 6), 193);\n}\n;\n_.init__V = function com_vaadin_client_ui_colorpicker_AbstractColorPickerConnector_init__V(){\n  com_google_gwt_lang_Cast_instanceOf__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Z(this.getWidget__Lcom_google_gwt_user_client_ui_Widget_2(), 48) && com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.getWidget__Lcom_google_gwt_user_client_ui_Widget_2(), 48).addClickHandler__Lcom_google_gwt_event_dom_client_ClickHandler_2Lcom_google_gwt_event_shared_HandlerRegistration_2(this);\n}\n;\n_.onStateChanged__Lcom_vaadin_client_communication_StateChangeEvent_2V = function com_vaadin_client_ui_colorpicker_AbstractColorPickerConnector_onStateChanged__Lcom_vaadin_client_communication_StateChangeEvent_2V(stateChangeEvent){\n  com_vaadin_client_ui_AbstractComponentConnector_$onStateChanged__Lcom_vaadin_client_ui_AbstractComponentConnector_2Lcom_vaadin_client_communication_StateChangeEvent_2V(this, stateChangeEvent);\n  if (stateChangeEvent.hasPropertyChanged__Ljava_lang_String_2Z($intern_420)) {\n    this.refreshColor__V();\n    (!this.com_vaadin_client_ui_AbstractConnector_state && (this.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_ui_colorpicker_ColorPickerState_showDefaultCaption && ((!this.com_vaadin_client_ui_AbstractConnector_state && (this.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_AbstractComponentState_caption == null || java_lang_String_$equals__Ljava_lang_String_2Ljava_lang_Object_2Z('', (!this.com_vaadin_client_ui_AbstractConnector_state && (this.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_AbstractComponentState_caption)) && this.setCaption__Ljava_lang_String_2V((!this.com_vaadin_client_ui_AbstractConnector_state && (this.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_ui_colorpicker_ColorPickerState_color);\n  }\n  if (stateChangeEvent.hasPropertyChanged__Ljava_lang_String_2Z($intern_376) || stateChangeEvent.hasPropertyChanged__Ljava_lang_String_2Z($intern_776) || stateChangeEvent.hasPropertyChanged__Ljava_lang_String_2Z($intern_1296)) {\n    this.setCaption__Ljava_lang_String_2V(com_vaadin_client_ui_colorpicker_AbstractColorPickerConnector_$getCaption__Lcom_vaadin_client_ui_colorpicker_AbstractColorPickerConnector_2Ljava_lang_String_2(this));\n    (!this.com_vaadin_client_ui_AbstractConnector_state && (this.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_ui_colorpicker_ColorPickerState_showDefaultCaption && ((!this.com_vaadin_client_ui_AbstractConnector_state && (this.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_AbstractComponentState_caption == null || !(!this.com_vaadin_client_ui_AbstractConnector_state && (this.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_AbstractComponentState_caption.length) && !(!this.com_vaadin_client_ui_AbstractConnector_state && (this.com_vaadin_client_ui_AbstractConnector_state = com_vaadin_client_ui_AbstractConnector_$createState__Lcom_vaadin_client_ui_AbstractConnector_2Lcom_vaadin_shared_communication_SharedState_2(this)) , com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Object_2(this.com_vaadin_client_ui_AbstractConnector_state, 6), 193)).com_vaadin_shared_AbstractComponentState_width.length?com_google_gwt_user_client_ui_UIObject_$addStyleName__Lcom_google_gwt_user_client_ui_UIObject_2Ljava_lang_String_2V(this.getWidget__Lcom_google_gwt_user_client_ui_Widget_2(), $intern_1308):com_google_gwt_user_client_ui_UIObject_$removeStyleName__Lcom_google_gwt_user_client_ui_UIObject_2Ljava_lang_String_2V(this.getWidget__Lcom_google_gwt_user_client_ui_Widget_2(), $intern_1308);\n  }\n}\n;\nvar com_google_gwt_lang_ClassLiteralHolder_Lcom_1vaadin_1client_1ui_1colorpicker_1AbstractColorPickerConnector_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Class_2Ljava_lang_Class_2($intern_1294, 'AbstractColorPickerConnector', 679);\nfunction com_vaadin_shared_ui_colorpicker_ColorPickerState_ColorPickerState__V(){\n  com_vaadin_shared_AbstractComponentState_AbstractComponentState__V.call(this);\n  this.com_vaadin_shared_AbstractComponentState_primaryStyleName = $intern_1301;\n}\n\ncom_google_gwt_lang_JavaClassHierarchySetupUtil_defineClass__Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2Lcom_google_gwt_core_client_JavaScriptObject_2(193, 6, {6:1, 47:1, 193:1, 3:1}, com_vaadin_shared_ui_colorpicker_ColorPickerState_ColorPickerState__V);\n_.com_vaadin_shared_ui_colorpicker_ColorPickerState_color = null;\n_.com_vaadin_shared_ui_colorpicker_ColorPickerState_popupVisible = false;\n_.com_vaadin_shared_ui_colorpicker_ColorPickerState_showDefaultCaption = false;\nvar com_google_gwt_lang_ClassLiteralHolder_Lcom_1vaadin_1shared_1ui_1colorpicker_1ColorPickerState_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Lcom_google_gwt_core_client_JavaScriptObject_2Ljava_lang_Class_2Ljava_lang_Class_2($intern_1174, 'ColorPickerState', 193);\n$entry(com_google_gwt_core_client_impl_AsyncFragmentLoader_onLoad__IV)(9);\n\n//# sourceURL=com.vaadin.tapio.googlemaps.demo.DemoWidgetset-9.js\n")
