<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name :</label>
            <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password :</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email :</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="***@***.com"/>
            </div>
        </div>
        </#if>
        <#if !isRegisterForm><a href="/registration">Registration</a> </#if>
        <button type="submit" class="btn btn-light"><#if isRegisterForm>Create <#else>Sign In</#if></button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-light">Sign out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>