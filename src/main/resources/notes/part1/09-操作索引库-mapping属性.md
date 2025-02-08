## 操作索引库-mapping属性
mapping是对索引库中文档的约束，包括字段类型、字段名称、字段是否存储等

### type
字段类型：
- text：全文本类型，用于存储文本内容，**进行分词**
- keyword：关键字类型，用于存储关键字，**不进行分词**

数值:
- integer：整数类型
- long：长整数类型
- float：单精度浮点数类型
- double：双精度浮点数类型  

日期类型: 
- date

布尔类型:
- boolean  

对象类型:
- object

### index
是否创建索引，默认为true，并不是所有字段都需要创建索引，比如id字段，邮箱字段等

### analyzer
分词器，用于对文本进行分词，只需要对text类型的字段进行设置即可

### properties
属性，用于对对象类型的字段进行设置，比如对user对象的name和age字段进行设置