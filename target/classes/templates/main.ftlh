<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div>
        <@l.logout/>
        <span><a href="/user">User List</a> </span>
    </div>
<div>
    <form method="post">
        <input type="text" name="text" placeholder="Enter message" />
        <input type="text" name="tag" placeholder="Тag">
        <button type="submit">Add</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter?ifExists}">
    <button type="submit">Search</button>
</form>
<div>Messages</div>
<#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
    </div>
    <#else>
    No messages
</#list>
</@c.page>