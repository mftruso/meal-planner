<g:if test="${flash.message}">
<div class="row">
    <div class="col-xs-12">
        <div class="card card-warning">
            <div class="card-header">
                <div class="header-block">
                    ${flash.message}
                </div>
            </div>
            <div class="card-block">
                <g:eachError bean="${category}">
                    <li><g:message error="${it}" /></li>
                </g:eachError>
            </div>
        </div>
    </div>
</div>
</g:if>