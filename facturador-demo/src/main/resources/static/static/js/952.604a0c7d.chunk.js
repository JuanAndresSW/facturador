"use strict";(self.webpackChunkfacturador_masmas=self.webpackChunkfacturador_masmas||[]).push([[952],{3136:function(e,t,r){r.r(t),r.d(t,{default:function(){return v}});var n=r(5861),a=r(885),s=r(7757),o=r.n(s),i=r(2791),c=r(3504),u=r(6871),l=r(6344),d=r(7692),p=r(7428),b=r(4112),f=r(8683),j=r(9245),g=r(1803);function x(e,t){return m.apply(this,arguments)}function m(){return(m=(0,n.Z)(o().mark((function e(t,r){var n;return o().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:n=function(e,n){201===e?((0,g.Z)((0,f.Z)((0,f.Z)({},JSON.parse(n)),{},{username:t.user.username,role:"MAIN",active:"0",pasive:"0"})),r(!0),window.location.reload()):r(!1,n)},(0,j.Z)("POST","mainaccounts",{body:JSON.stringify(t)},n);case 2:case"end":return e.stop()}}),e)})))).apply(this,arguments)}var Z=r(184);function v(){var e=(0,u.s0)(),t=(0,i.useState)("user"),r=(0,a.Z)(t,2),s=r[0],f=r[1],j=(0,i.useState)(!1),g=(0,a.Z)(j,2),m=g[0],v=g[1],y=(0,i.useState)(!1),S=(0,a.Z)(y,2),O=S[0],w=S[1],h=(0,i.useState)(""),N=(0,a.Z)(h,2),P=N[0],C=N[1],k=(0,i.useState)(""),I=(0,a.Z)(k,2),D=I[0],E=I[1],T=(0,i.useState)(""),U=(0,a.Z)(T,2),q=U[0],z=U[1],L=(0,i.useState)(),M=(0,a.Z)(L,2),A=M[0],J=M[1],K=(0,i.useState)(""),X=(0,a.Z)(K,2),Y=X[0],_=X[1],F=(0,i.useState)(""),R=(0,a.Z)(F,2),V=R[0],B=R[1],G=(0,i.useState)(""),H=(0,a.Z)(G,2),Q=H[0],W=H[1],$=(0,i.useState)(""),ee=(0,a.Z)($,2),te=ee[0],re=ee[1],ne=(0,i.useState)(""),ae=(0,a.Z)(ne,2),se=ae[0],oe=ae[1],ie=(0,i.useState)(""),ce=(0,a.Z)(ie,2),ue=ce[0],le=ce[1],de=(0,i.useState)(""),pe=(0,a.Z)(de,2),be=pe[0],fe=pe[1];function je(){return(je=(0,n.Z)(o().mark((function e(){var t;return o().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:t={user:{username:P,email:D,password:q,avatar:A},trader:{businessName:Q,vatCategory:te,code:se,grossIncome:ue}},v(!0),x(t,ge);case 3:case"end":return e.stop()}}),e)})))).apply(this,arguments)}function ge(t,r){v(!1),t?(w(!0),fe(""),e("/inicio")):fe(r)}return"user"===s?(0,Z.jsxs)(l.l0,{title:"Datos de la cuenta",onSubmit:function(){if(B(""),b.Z.names(P,B)&&b.Z.email(D,B)&&b.Z.password(q,B))return q!==Y?B("Las contrase\xf1as no coinciden"):void(b.Z.image(A,B)&&f("trader"))},children:[(0,Z.jsx)(c.rU,{to:"/",children:(0,Z.jsx)(d.OKt,{})}),(0,Z.jsx)(l.gN,{icon:(0,Z.jsx)(d.sXe,{}),label:"\xbfC\xf3mo quieres que te identifiquemos?",bind:[P,C],validator:b.Z.names(P)}),(0,Z.jsx)(l.gN,{icon:(0,Z.jsx)(d.f7N,{}),label:"Tu direcci\xf3n de correo electr\xf3nico",bind:[D,E],validator:b.Z.email(D)}),(0,Z.jsx)(l.gN,{icon:(0,Z.jsx)(d.XTK,{}),label:"Elige una contrase\xf1a",bind:[q,z],type:"password",validator:b.Z.password(q)}),(0,Z.jsx)(l.gN,{label:"Vuelve a escribir la contrase\xf1a",bind:[Y,_],type:"password",validator:q===Y}),(0,Z.jsx)(l.Ee,{label:"Foto de perfil",note:"(opcional)",setter:J,img:A}),(0,Z.jsx)(l.v0,{type:"error",message:V}),(0,Z.jsx)(l.zx,{type:"submit",text:"Siguiente"}),(0,Z.jsxs)("p",{style:{textAlign:"center",cursor:"default"},children:["\xbfYa tienes una cuenta? ",(0,Z.jsx)(c.rU,{to:"/ingresar",style:{textDecoration:"none"},children:"Ingresar"})]})]}):"trader"===s?(0,Z.jsxs)(l.l0,{title:"Datos del comercio",onSubmit:function(){return fe(""),b.Z.names(Q)?b.Z.vatCategory(te,fe)?b.Z.code(se)?void(b.Z.code(ue,fe)&&function(){je.apply(this,arguments)}()):fe("Ingrese un".concat("Monotributista"===te?" C.U.I.L. v\xe1lido":"a C.U.I.T. v\xe1lida")):void 0:fe("La raz\xf3n social debe ser de entre 3 y 20 caracteres")},children:[m?null:(0,Z.jsx)(d.gbs,{onClick:function(){return f("user")}}),(0,Z.jsx)(l.gN,{icon:(0,Z.jsx)(d.xa5,{}),label:"Escribe tu raz\xf3n social",bind:[Q,W],validator:b.Z.names(Q)}),(0,Z.jsx)(l.Y8,{legend:"Selecciona una categor\xeda:",bind:[te,re],options:["Responsable Inscripto","Monotributista","Sujeto Exento"]}),(0,Z.jsx)(l.gN,{label:"C.U.I."+("Monotributista"===te?"L.":"T."),note:"(si no eliges uno, se generar\xe1 uno falso)",bind:[se,oe],validator:b.Z.code(se),icon:(0,Z.jsx)(d.Odq,{})}),(0,Z.jsx)(l.gN,{label:"N\xfamero de ingresos brutos",note:"(si no eliges uno, se generar\xe1 uno falso)",bind:[ue,le],icon:(0,Z.jsx)(d.cf2,{}),validator:b.Z.code(ue)}),(0,Z.jsx)(l.v0,{type:"error",message:be}),O?(0,Z.jsx)(l.v0,{type:"success",message:'Se ha creado la cuenta "'.concat(D,'"')}):m?(0,Z.jsx)(p.gb,{}):(0,Z.jsx)(l.zx,{type:"submit",text:"Enviar"})]}):null}},8683:function(e,t,r){function n(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function a(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function s(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?a(Object(r),!0).forEach((function(t){n(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):a(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}r.d(t,{Z:function(){return s}})}}]);
//# sourceMappingURL=952.604a0c7d.chunk.js.map