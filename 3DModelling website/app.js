
// Variables

 let container;
 let camera;
 let renderer;
 let scene;
 let world;

 function init(){
     container = document.querySelector('.scene');
 
    // creating Scene
    scene = new THREE.Scene();

    const fov = 35;
    const aspect = container.clientWidth / container.clientHeight;
    const near = 0.1;
    const far =  1000;
    // camera setup
    camera = new THREE.PerspectiveCamera(fov,aspect,near,far);
    camera.position.set(0,0,20);
    const ambient= new THREE.AmbientLight(0x404040,2);
    scene.add(ambient);

    const light = new THREE.DirectionalLight(0xffffff,2);

    //renderer
    renderer = new THREE.WebGLRenderer({antilalias:true, alpha:true});
    renderer.setSize(container.clientWidth, container.clientHeight);
    renderer.setPixelRatio(window.devicePixelRatio);

    container.appendChild(renderer.domElement);
    
    //Load Model
    let loader = new THREE.GLTFLoader();
    loader.load("./3d/scene.gltf", function(gltf){ 
    scene.add(gltf.scene);          
    world =gltf.scene.children[0];
    animate();
    });

}

function animate(){
    requestAnimationFrame(animate);
    world.rotation.z += 0.005
    renderer.render(scene, camera);
}
init();

function windowResize(){
    camera.aspect = container.clientWidth / container.clientHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(container.clientWidth, container.clientHeight);
}

window.addEventListener("resize",onWindowResize);





