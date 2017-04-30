<aside class="sidebar">
    <div class="sidebar-container">
        <div class="sidebar-header">
            <div class="brand">
                <div class="logo"> <span class="l l1"></span> <span class="l l2"></span> <span class="l l3"></span> <span class="l l4"></span> <span class="l l5"></span> </div> Meal Planner </div>
        </div>
        <nav class="menu">
            <ul class="nav metismenu" id="sidebar-menu">
                <li class="${request.forwardURI.contains('meal') ? 'active' : ''}">
                    <a href="${createLink(controller: 'meal', action: 'list')}"> <i class="fa fa-home"></i> Calendar </a>
                </li>
                <li class="${request.forwardURI.contains('dish/') ? 'active' : ''}">
                    <a href="${createLink(controller: 'dish', action: 'list')}"> <i class="fa fa-bar-chart"></i> Dishes</a>
                </li>
                <li class="${request.forwardURI.contains('category') ? 'active' : ''}">
                    <a href="${createLink(controller: 'category', action: 'list')}"> <i class="fa fa-table"></i> Categories </a>
                </li>
            </ul>
        </nav>
    </div>
    <footer class="sidebar-footer">
        %{--<ul class="nav metismenu" id="customize-menu">--}%
        %{--</ul>--}%
    </footer>
</aside>
<div class="sidebar-overlay" id="sidebar-overlay"></div>