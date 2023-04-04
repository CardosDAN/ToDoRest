<script>
    import * as THREE from 'three';
    import {NavbarBrand} from "sveltestrap";
    import {onMount} from "svelte";

    let canvasRef;

    onMount(() => {
        const scene = new THREE.Scene();
        const camera = new THREE.PerspectiveCamera(
            75,
            canvasRef.clientWidth / canvasRef.clientHeight,
            0.1,
            1000
        );
        const renderer = new THREE.WebGLRenderer({ antialias: true });
        renderer.setSize(canvasRef.clientWidth, canvasRef.clientHeight);
        canvasRef.appendChild(renderer.domElement);

        const geometry = new THREE.TorusKnotGeometry(10, 3, 100, 16);
        const material = new THREE.MeshBasicMaterial({ color: 0xff0000 });
        const knot = new THREE.Mesh(geometry, material);
        scene.add(knot);
        scene.background = new THREE.Color(0xffffff);

        camera.position.z = 50;

        const animate = function () {
            requestAnimationFrame(animate);

            knot.rotation.x += 0.01;
            knot.rotation.y += 0.01;

            renderer.render(scene, camera);
        };

        animate();
    });
</script>

<NavbarBrand>
    <canvas bind:this={canvasRef}></canvas>
</NavbarBrand>

<style>
    canvas {
        width: 300px;
        height: 300px;
    }
</style>
